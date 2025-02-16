package kr.performuse.performuseserver.global.error

enum class ErrorCode(
    val value: Int,
) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
}
