package ua.edu.ukma.tuztech.entity

import jakarta.persistence.*

@Entity
class Barber(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val firstName: String,

    val lastName: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "barbershop_id")
    val barbershop: Barbershop
)