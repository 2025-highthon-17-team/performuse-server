package kr.performuse.performuseserver.global.filter

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.filter.OncePerRequestFilter

abstract class BaseFilter() : OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.startsWith("/swagger-ui") || request.requestURI.startsWith("/v3")
    }

}
