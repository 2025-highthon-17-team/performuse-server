package kr.performuse.performuseserver.domain.auth.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

class LoginRequest(

    @Schema(example = "example")
    @field:Pattern(
        regexp = "^[a-zA-Z](?:[a-zA-Z\\d]{4,16})?$",
        message = "아이디는 영어, 숫자 포함 4~16자여야 해요",
    )
    val identifier: String,

    @Schema(example = "example1234")
    @field:Pattern(
        message = "비밀번호는 영어, 숫자, 특수문자 포함 8~512자여야 해요",
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)([A-Za-z\\d@#$%^&+=!?*~]+){8,512}$",
    )
    val password: String,

)
