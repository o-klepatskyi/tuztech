package ua.edu.ukma.tuztech.entity

import jakarta.persistence.*

@Entity
class Barbershop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val address: String,

    @Column(nullable = true)
    val lat: Double?,

    @Column(nullable = true)
    val lng: Double?
)
