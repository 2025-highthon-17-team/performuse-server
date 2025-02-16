package kr.performuse.performuseserver.global.response

import org.slf4j.MDC
import org.springframework.http.HttpStatus

class DataResponse<T>(
    val traceId: String = MDC.get("traceId"),
    val uri: String = MDC.get("uri"),
    val status: Int,
    val code: String,
    val message: String,
    val data: T,
) {

    companion object {

        fun <T> ok(
            message: String,
            data: T,
        ): DataResponse<T> = createResponse(HttpStatus.OK, message, data)

        fun <T> created(
            message: String,
            data: T,
        ): DataResponse<T> = createResponse(HttpStatus.CREATED, message, data)

        private fun <T> createResponse(
            status: HttpStatus,
            message: String,
            data: T,
        ): DataResponse<T> =
            DataResponse(
                status = status.value(),
                code = status.reasonPhrase,
                message = message,
                data = data,
            )
    }

}
