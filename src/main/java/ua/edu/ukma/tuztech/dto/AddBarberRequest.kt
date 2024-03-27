package ua.edu.ukma.tuztech.dto

data class AddBarberRequest(
    val firstName: String,
    val lastName: String,
    val barbershopId: Long,
    val imageUrl: String
)