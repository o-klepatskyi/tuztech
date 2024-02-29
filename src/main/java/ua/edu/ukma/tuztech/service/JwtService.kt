package ua.edu.ukma.tuztech.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.entity.User
import java.security.Key
import java.util.*

@Service
class JwtService {
    private val secretKey = "4428472B4B6250655368566D597133743677397A244326452948404D63516654"

    fun generateToken(userDetails: User): String = generateToken(mapOf(), userDetails)

    fun generateToken(
            claims: Map<String, Any>,
            userDetails: User
    ): String {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.email)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact()
    }


    fun validateToken(jwtToken: String, userDetails: UserDetails): Boolean {
        val username = extractLogin(jwtToken)
        return username == userDetails.username && !isTokenExpired(jwtToken)
    }

    private fun isTokenExpired(jwtToken: String): Boolean {
        return extractExpiration(jwtToken).before(Date(System.currentTimeMillis()))
    }

    private fun extractExpiration(jwtToken: String): Date = extractClaim(jwtToken, Claims::getExpiration)


    fun extractLogin(jwtToken: String): String? = extractClaim(jwtToken, Claims::getSubject)

    fun <T> extractClaim(jwtToken: String, claimResolver: (Claims) -> T): T {
        val claims = extractAllClaims(jwtToken)
        return claimResolver(claims)
    }

    private fun extractAllClaims(jwtToken: String): Claims = Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(jwtToken)
            .body

    private fun getKey(): Key {
        val bytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(bytes)
    }
}