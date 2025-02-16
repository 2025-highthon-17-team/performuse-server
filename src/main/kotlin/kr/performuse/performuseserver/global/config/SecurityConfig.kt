package kr.performuse.performuseserver.global.config

import kr.performuse.performuseserver.global.filter.ExceptionFilter
import kr.performuse.performuseserver.global.filter.JwtFilter
import kr.performuse.performuseserver.global.filter.MDCFilter
import kr.performuse.performuseserver.global.filter.ResponseFilter
import kr.performuse.performuseserver.global.resolver.CustomAccessDeniedHandler
import kr.performuse.performuseserver.global.resolver.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val mdcFilter: MDCFilter,
    private val exceptionFilter: ExceptionFilter,
    private val responseFilter: ResponseFilter,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfiguration()) }
            .authorizeHttpRequests {

                it.requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                it.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                it.requestMatchers("/error").permitAll()

                it.anyRequest().permitAll()

            }
            .exceptionHandling {
                it.accessDeniedHandler(customAccessDeniedHandler)
                it.authenticationEntryPoint(customAuthenticationEntryPoint)
            }
            .addFilterBefore(responseFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtFilter, ResponseFilter::class.java)
            .addFilterBefore(mdcFilter, JwtFilter::class.java)
            .addFilterBefore(exceptionFilter, MDCFilter::class.java)
            .build()

    fun corsConfiguration(): CorsConfigurationSource {
        return CorsConfigurationSource {
            CorsConfiguration().apply {
                allowedHeaders = listOf("*")
                allowedMethods = listOf("*")
                allowedOriginPatterns = listOf("*")
                allowCredentials = true
            }
        }
    }

}
