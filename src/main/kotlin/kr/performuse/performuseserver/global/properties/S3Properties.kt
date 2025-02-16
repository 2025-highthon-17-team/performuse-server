package kr.performuse.performuseserver.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.cloud.aws.s3")
class S3Properties(
    val bucket: String,
)
