package ua.edu.ukma.tuztech.dto

data class AuthenticationRequest constructor(
    val email: String,
    val password: String
)