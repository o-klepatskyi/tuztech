package ua.edu.ukma.tuztech.dto

data class EditBarbershopRequest(
    val name: String,
    val address: String,
    val lat: Double?,
    val lng: Double?,
    val imageUrl: String
)