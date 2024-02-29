package ua.edu.ukma.tuztech.dto

data class EditUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)