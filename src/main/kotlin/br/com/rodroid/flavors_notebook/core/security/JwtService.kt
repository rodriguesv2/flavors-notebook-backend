package br.com.rodroid.flavors_notebook.core.security

import br.com.rodroid.flavors_notebook.domain.user.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(

    @Value("\${jwt.secret:uma-chave-secreta-muito-grande-para-o-nosso-caderno-de-sabores-api-segura}")
    private val secretString: String,

    @Value("\${jwt.expiration:86400000}")
    private val expirationTime: Long,
) {
    private val secretKey: SecretKey
        get() = Keys.hmacShaKeyFor(secretString.toByteArray())

    fun generateToken(user: User): String {
        return Jwts.builder()
            .subject(user.email)
            .claim("name", user.name)
            .claim("id", user.id.toString())
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(secretKey)
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}