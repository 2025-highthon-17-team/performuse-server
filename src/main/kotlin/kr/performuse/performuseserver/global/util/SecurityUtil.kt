package kr.performuse.performuseserver.global.util

import kr.performuse.performuseserver.global.jwt.JwtUtil
import kr.performuse.performuseserver.global.security.SecurityUserDetailService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityUtil(
    private val jwtUtil: JwtUtil,
    private val securityUserDetailService: SecurityUserDetailService,
) {

    fun getTsid(): String = SecurityContextHolder.getContext().authentication.name

    fun setAuthentication(accessToken: String) {

        val tsid = jwtUtil.getTsidByAccessToken(accessToken)

        val authentication = securityUserDetailService.loadUserByUsername(tsid).let {
            UsernamePasswordAuthenticationToken(it.username, it.password, it.authorities)
        }

        SecurityContextHolder.clearContext()
        SecurityContextHolder.getContext().authentication = authentication

    }

}