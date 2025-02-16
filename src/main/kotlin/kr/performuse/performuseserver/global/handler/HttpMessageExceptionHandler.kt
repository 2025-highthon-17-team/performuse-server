package kr.performuse.performuseserver.global.handler

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import kr.performuse.performuseserver.global.response.Response
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class HttpMessageExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    private fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): Response {
        return when (e.cause) {
            is MismatchedInputException -> mismatchedInputException(e.cause as MismatchedInputException)
            is JsonParseException -> jsonParseException(e.cause as JsonParseException)
            else -> throw e
        }
    }

    private fun mismatchedInputException(cause: MismatchedInputException): Response {
        return Response(
            status = HttpStatus.BAD_REQUEST.value(),
            code = cause.javaClass.simpleName,
            message = "요청 Body에 ${cause.path[0].fieldName}이(가) 빠졌어요",
        )
    }

    private fun jsonParseException(cause: JsonParseException): Response {
        return Response(
            status = HttpStatus.BAD_REQUEST.value(),
            code = cause.javaClass.simpleName,
            message = cause.originalMessage,
        )
    }

}
