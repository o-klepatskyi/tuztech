package ua.edu.ukma.tuztech.dto

data class AddBarbershopRequest(
    val name: String,
    val address: String,
    val lat: Double?,
    val lng: Double?
)