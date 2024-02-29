package ua.edu.ukma.tuztech.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.edu.ukma.tuztech.service.AuthService
import ua.edu.ukma.tuztech.dto.AuthenticationRequest
import ua.edu.ukma.tuztech.dto.AuthenticationResponse

@RestController
@RequestMapping("/auth")
class AuthController @Autowired constructor(
    private val authService: AuthService
){

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        return authService.authenticate(authRequest)
    }

}