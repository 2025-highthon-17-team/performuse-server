package kr.performuse.performuseserver.global.util

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordUtil(
    private val encoder: Argon2PasswordEncoder,
) {

    fun encode(password: String): String {
        return encoder.encode(password)
    }

    fun match(
        originPassword: String,
        encryptedPassword: String,
    ): Boolean {
        return encoder.matches(originPassword, encryptedPassword)
    }

}
