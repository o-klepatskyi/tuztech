package ua.edu.ukma.tuztech.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.AuthenticationRequest
import ua.edu.ukma.tuztech.dto.AuthenticationResponse

@Service
class AuthService @Autowired constructor(
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
){

    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val user = userService.getUserByEmail(authRequest.email) ?: throw UsernameNotFoundException("User with email ${authRequest.email} not found")
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token)
    }
}