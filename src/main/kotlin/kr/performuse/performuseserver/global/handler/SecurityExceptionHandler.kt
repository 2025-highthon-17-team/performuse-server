package kr.performuse.performuseserver.global.handler

import kr.performuse.performuseserver.global.response.Response
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class SecurityExceptionHandler {

    @ExceptionHandler(InsufficientAuthenticationException::class)
    private fun handleInsufficientAuthenticationException(): Response {
        return Response(
            status = HttpStatus.UNAUTHORIZED.value(),
            code = HttpStatus.UNAUTHORIZED.reasonPhrase,
            message = "헤더에 토큰이 담기지 않았어요",
        )
    }

    @ExceptionHandler(AccessDeniedException::class)
    private fun handleAccessDeniedException(): Response {
        return Response(
            status = HttpStatus.FORBIDDEN.value(),
            code = HttpStatus.FORBIDDEN.reasonPhrase,
            message = "API에 접근하기 위한 권한이 없어요",
        )
    }

}
