package ua.edu.ukma.tuztech.dto

data class EditBarberRequest(
    val firstName: String,
    val lastName: String,
    val barbershopId: Long
)