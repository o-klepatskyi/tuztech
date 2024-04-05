package ua.edu.ukma.tuztech.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import ua.edu.ukma.tuztech.dto.AuthenticationRequest
import ua.edu.ukma.tuztech.entity.User

class AuthServiceTest {

    private val jwtService = mock(JwtService::class.java)
    private val authenticationManager = mock(AuthenticationManager::class.java)
    private val userService = mock(UserService::class.java)

    private val authService = AuthService(jwtService, authenticationManager, userService)

    @Test
    fun `authenticate should return AuthenticationResponse`() {
        val authRequest = AuthenticationRequest("admin@tuz.com", "admin")
        val user = User(1, "admin", "admin", "admin@tuz.com", "admin")
        val expectedToken = "mocked_token"

        `when`(userService.getUserByEmail(authRequest.email)).thenReturn(user)
        `when`(jwtService.generateToken(user)).thenReturn(expectedToken)
        `when`(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken::class.java)))
            .thenReturn(mock(Authentication::class.java))

        val result = authService.authenticate(authRequest)

        assertNotNull(result)
        assertEquals(expectedToken, result.token)
    }

    @Test
    fun `authenticate should throw UsernameNotFoundException if user not found`() {
        val authRequest = AuthenticationRequest("nonexistent@example.com", "password")

        `when`(userService.getUserByEmail(authRequest.email)).thenReturn(null)

        assertThrows(UsernameNotFoundException::class.java) {
            authService.authenticate(authRequest)
        }
    }
}