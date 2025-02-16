package kr.performuse.performuseserver.global.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import kr.performuse.performuseserver.global.response.Response
import kr.performuse.performuseserver.global.error.BusinessException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.async.AsyncRequestTimeoutException

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(BusinessException::class)
    private fun handleBusinessException(e: BusinessException): Response {
        logger.warn { e.message }
        return Response(
            status = e.errorCode.value,
            code = e.javaClass.simpleName,
            message = e.message,
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    private fun handleValidationException(e: MethodArgumentNotValidException): Response {
        return Response(
            status = HttpStatus.BAD_REQUEST.value(),
            code = e.javaClass.simpleName,
            message = e.fieldErrors.joinToString(", ") { "${it.defaultMessage} (${it.field})" },
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    private fun handleMethodNotAllowedException(e: HttpRequestMethodNotSupportedException): Response {
        return Response(
            status = HttpStatus.METHOD_NOT_ALLOWED.value(),
            code = e.javaClass.simpleName,
            message = "${e.method} 대신 ${e.supportedHttpMethods}를 써주세요",
        )
    }

    @ExceptionHandler(RuntimeException::class)
    private fun handleRuntimeException(e: RuntimeException): Response {
        logger.error { e.stackTraceToString() }
        return Response(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            code = e.javaClass.simpleName,
            message = e.toString(),
        )
    }

}
