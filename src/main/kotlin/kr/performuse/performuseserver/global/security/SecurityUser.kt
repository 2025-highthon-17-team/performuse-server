package kr.performuse.performuseserver.global.security

import kr.performuse.performuseserver.domain.user.entity.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
    private val user: User,
) : UserDetails {

    override fun getUsername() = user.tsid.toString()

    override fun getPassword() = user.password

    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority("USER"))

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}
