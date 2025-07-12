package com.shashinthalk.taskmanagerbackend.security.jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil {
    private val secret = "your-256-bit-secret-your-256-bit-secret-"
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())
    private val expirationMs = 3600000L

    fun generateToken(username: String): String =
        Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(key, Jwts.SIG.HS256)
            .compact()

    fun getEmailFromToken(token: String): String =
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject

    fun validateToken(token: String): Boolean =
        try {
            val claims =
                Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .payload
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
}
