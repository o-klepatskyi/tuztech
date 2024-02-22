package ua.edu.ukma.tuztech.entity

import jakarta.persistence.*

@Entity
@Table(name="Role")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,
    @Column(nullable = false)
    val name: String,
)