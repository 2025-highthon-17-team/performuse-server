package kr.performuse.performuseserver.global.jwt

import java.time.Duration

enum class TokenType(val expiration: Duration) {
    ACCESS(Duration.ofDays(15)),
    REFRESH(Duration.ofDays(30)),
}
