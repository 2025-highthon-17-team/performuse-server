package kr.performuse.performuseserver.global.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import kr.performuse.performuseserver.domain.user.entity.User
import kr.performuse.performuseserver.global.jwt.exception.ExpiredTokenException
import kr.performuse.performuseserver.global.jwt.exception.InvalidTokenException
import kr.performuse.performuseserver.global.jwt.exception.SignatureNotMatchException
import kr.performuse.performuseserver.global.jwt.exception.TokenNotProvidedException
import kr.performuse.performuseserver.global.properties.JwtProperties
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class JwtUtil(
    private val jwtProperties: JwtProperties,
) {

    fun generateToken(
        user: User,
        tokenType: TokenType,
    ): String {

        val key = when (tokenType) {
            TokenType.ACCESS -> jwtProperties.accessKey
            TokenType.REFRESH -> jwtProperties.refreshKey
        }

        val claims = mapOf(
            "tsid" to user.tsid,
            "email" to user.email,
        )

        return createToken(claims, tokenType.expiration, key)
    }

    fun getTsidByAccessToken(token: String): String {
        return try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.accessKey.toByteArray(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .payload["tsid"].toString()
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: IllegalArgumentException) {
            throw TokenNotProvidedException()
        } catch (e: UnsupportedJwtException) {
            throw InvalidTokenException()
        } catch (e: MalformedJwtException) {
            throw InvalidTokenException()
        } catch (e: SignatureException) {
            throw SignatureNotMatchException()
        }
    }

    private fun createToken(
        claims: Map<String, Any>,
        expiration: Duration,
        key: String,
    ): String {

        val expirationDuration = Date.from(Instant.now().plus(expiration))

        return Jwts.builder()
            .header()
            .add("typ", "JWT")
            .add("alg", Jwts.SIG.HS256.id)
            .and()
            .claims(claims)
            .expiration(expirationDuration)
            .signWith(Keys.hmacShaKeyFor(key.toByteArray(StandardCharsets.UTF_8)), Jwts.SIG.HS256)
            .compact()
    }

}
