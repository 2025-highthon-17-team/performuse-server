package kr.performuse.performuseserver.global.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.performuse.performuseserver.global.util.SecurityUtil
import org.springframework.stereotype.Component

@Component
class JwtFilter(
    private val securityUtil: SecurityUtil,
) : BaseFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        if (request.getHeader("Authorization") != null) {
            val accessToken = request.getHeader("Authorization").removePrefix("Bearer ")
            securityUtil.setAuthentication(accessToken)
        }

        filterChain.doFilter(request, response)

    }

}
