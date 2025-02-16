package kr.performuse.performuseserver.global.config

import kr.performuse.performuseserver.global.properties.JwtProperties
import kr.performuse.performuseserver.global.properties.S3Properties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    S3Properties::class,
    JwtProperties::class,
)
class PropertiesConfig
