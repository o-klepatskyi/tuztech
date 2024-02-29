package ua.edu.ukma.tuztech.filter

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import ua.edu.ukma.tuztech.service.JwtService

@Component
class JwtFilter(
        private val jwtService: JwtService,
        private val userDetailService: UserDetailsService,
        @Qualifier("handlerExceptionResolver")
        private val exceptionResolver: HandlerExceptionResolver
): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authenticationHeader: String? = request.getHeader("Authorization")
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            val jwtToken = authenticationHeader.substring(7)
            val userLogin = jwtService.extractLogin(jwtToken)
            if (userLogin != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailService.loadUserByUsername(userLogin)
                if (jwtService.validateToken(jwtToken, userDetails)) {
                    val authToken = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null, userDetails.authorities
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        } catch (ex: ExpiredJwtException) {
            exceptionResolver.resolveException(request, response, null, ex)
        } catch (ex: Exception) {
            exceptionResolver.resolveException(request, response, null, ex)
        }
        filterChain.doFilter(request, response)
    }
}

