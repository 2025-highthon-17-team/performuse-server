package kr.performuse.performuseserver.global.util

import io.awspring.cloud.s3.S3Template
import kr.performuse.performuseserver.global.properties.S3Properties
import org.springframework.stereotype.Component

@Component
class S3Util(
    private val s3Template: S3Template,
    private val s3Properties: S3Properties,
) {

    fun uploadImage(fileName: String, imageBytes: ByteArray): String {

        val result = s3Template.upload(s3Properties.bucket, fileName, imageBytes.inputStream())

        return result.url.toString()
    }

}