package kr.performuse.performuseserver.global.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MDCFilter : BaseFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val traceId = UUID.randomUUID().toString()

        MDC.put("traceId", traceId)
        MDC.put("uri", request.requestURI)

        filterChain.doFilter(request, response)

        MDC.remove("traceId")
        MDC.remove("uri")

    }

}
