package kr.performuse.performuseserver.global.error

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String,
) : RuntimeException(message)
