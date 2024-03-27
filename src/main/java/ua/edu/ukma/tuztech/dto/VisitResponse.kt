package ua.edu.ukma.tuztech.dto

import java.time.LocalDateTime

data class VisitResponse(
    val id: Long,
    val barber: BarberResponse,
    val barbershop: BarbershopResponse,
    val user: UserResponse,
    val datetime: LocalDateTime,
    val durationMin: Int
)

data class BarberResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val imageUrl: String
)

data class BarbershopResponse(
    val id: Long,
    val name: String,
    val address: String,
    val lat: Double?,
    val lng: Double?,
    val imageUrl: String
)

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val password: String
)
