package kr.performuse.performuseserver.global.response

import org.slf4j.MDC
import org.springframework.http.HttpStatus

class Response(
    val traceId: String = MDC.get("traceId"),
    val uri: String = MDC.get("uri"),
    val status: Int,
    val code: String,
    val message: String,
) {

    companion object {

        fun ok(message: String): Response = createResponse(HttpStatus.OK, message)

        fun created(message: String): Response = createResponse(HttpStatus.CREATED, message)

        private fun createResponse(
            status: HttpStatus,
            message: String,
        ): Response {
            return Response(
                status = status.value(),
                code = status.reasonPhrase,
                message = message,
            )
        }

    }

}
