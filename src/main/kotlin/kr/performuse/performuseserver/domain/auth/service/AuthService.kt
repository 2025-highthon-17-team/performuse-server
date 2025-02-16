package kr.performuse.performuseserver.domain.auth.service

import kr.performuse.performuseserver.domain.auth.presentation.dto.LoginRequest
import kr.performuse.performuseserver.domain.auth.presentation.dto.SignupRequest
import kr.performuse.performuseserver.domain.auth.presentation.dto.TokenResponse
import kr.performuse.performuseserver.domain.user.entity.User
import kr.performuse.performuseserver.domain.user.exception.EmailAlreadyExistException
import kr.performuse.performuseserver.domain.user.exception.IdentifierAlreadyExistException
import kr.performuse.performuseserver.domain.user.exception.NicknameAlreadyExistException
import kr.performuse.performuseserver.domain.user.exception.PasswordNotMatchedException
import kr.performuse.performuseserver.domain.user.exception.UserNotFoundException
import kr.performuse.performuseserver.domain.user.repository.UserRepository
import kr.performuse.performuseserver.global.jwt.JwtUtil
import kr.performuse.performuseserver.global.jwt.TokenType
import kr.performuse.performuseserver.global.util.PasswordUtil
import kr.performuse.performuseserver.global.util.TsidUtil
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val tsidUtil: TsidUtil,
    private val passwordUtil: PasswordUtil,
    private val userRepository: UserRepository,
) {

    fun signup(request: SignupRequest): TokenResponse {

        if (userRepository.existsByIdentifier(request.identifier)) {
            throw IdentifierAlreadyExistException()
        }

        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistException()
        }

        if (userRepository.existsByNickname(request.nickname)) {
            throw NicknameAlreadyExistException()
        }

        val encryptedPassword = passwordUtil.encode(request.password)

        val user = User(
            tsid = tsidUtil.createTsid(),
            identifier = request.identifier,
            password = encryptedPassword,
            email = request.email,
            nickname = request.nickname,
            createdDate = LocalDateTime.now(),
            lastModifiedDate = LocalDateTime.now(),
        )

        userRepository.save(user)

        return TokenResponse(
            accessToken = jwtUtil.generateToken(user, TokenType.ACCESS),
            refreshToken = jwtUtil.generateToken(user, TokenType.REFRESH),
        )
    }

    fun login(request: LoginRequest): TokenResponse {

        val user = userRepository.findByIdentifier(request.identifier) ?: throw UserNotFoundException()

        if (passwordUtil.match(request.password, user.password).not()) throw PasswordNotMatchedException()

        return TokenResponse(
            accessToken = jwtUtil.generateToken(user, TokenType.ACCESS),
            refreshToken = jwtUtil.generateToken(user, TokenType.REFRESH),
        )
    }

}