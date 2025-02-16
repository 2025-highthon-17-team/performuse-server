package kr.performuse.performuseserver.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class JwtProperties(
    val accessKey: String,
    val refreshKey: String,
)