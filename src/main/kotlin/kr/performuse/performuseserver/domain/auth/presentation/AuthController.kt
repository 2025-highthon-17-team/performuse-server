package kr.performuse.performuseserver.domain.auth.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.performuse.performuseserver.domain.auth.presentation.dto.LoginRequest
import kr.performuse.performuseserver.domain.auth.presentation.dto.SignupRequest
import kr.performuse.performuseserver.domain.auth.presentation.dto.TokenResponse
import kr.performuse.performuseserver.domain.auth.service.AuthService
import kr.performuse.performuseserver.global.response.DataResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "Auth API 문서")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    fun signup(
        @Valid @RequestBody request: SignupRequest,
    ): DataResponse<TokenResponse> {
        val response = authService.signup(request)
        return DataResponse.ok("회원가입이 완료되었어요", response)
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    fun login(
        @Valid @RequestBody request: LoginRequest,
    ): DataResponse<TokenResponse> {
        val response = authService.login(request)
        return DataResponse.ok("로그인이 완료되었어요", response)
    }

}