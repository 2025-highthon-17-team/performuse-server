package kr.performuse.performuseserver.global.security

import kr.performuse.performuseserver.domain.user.exception.UserNotFoundException
import kr.performuse.performuseserver.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String) = SecurityUser(
        userRepository.findByTsid(username) ?: throw UserNotFoundException()
    )

}
