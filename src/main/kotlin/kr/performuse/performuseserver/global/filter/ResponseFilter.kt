package kr.performuse.performuseserver.global.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.performuse.performuseserver.global.wrapper.CustomCachingResponseWrapper
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class ResponseFilter : BaseFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val responseWrapper = CustomCachingResponseWrapper(response)

        filterChain.doFilter(request, responseWrapper)

        val status = responseWrapper.getCachedContent().takeIf { it.isNotEmpty() }
            ?.let { jacksonObjectMapper().readTree(it)["status"]?.asInt() }

        response.apply {
            this.status = status ?: 200
            this.contentType = responseWrapper.contentType
        }

    }

}
