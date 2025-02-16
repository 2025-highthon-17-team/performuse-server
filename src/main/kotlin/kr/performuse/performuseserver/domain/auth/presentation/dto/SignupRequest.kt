package kr.performuse.performuseserver.domain.auth.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

class SignupRequest(

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

    @Schema(example = "example@toongether.kr")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$",
        message = "이메일 형식이 맞지 않아요",
    )
    val email: String,

    @Schema(example = "예시")
    @field:Pattern(
        regexp = "^[^ ]{2,15}$",
        message = "닉네임은 2~15자여야 해요",
    )
    val nickname: String,

)
