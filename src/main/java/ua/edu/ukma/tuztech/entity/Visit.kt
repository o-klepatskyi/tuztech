package ua.edu.ukma.tuztech.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Visit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id")
    val barber: Barber,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "datetime", columnDefinition = "TIMESTAMP")
    val datetime: LocalDateTime,

    @Column(name="duration_min", nullable = false)
    val durationMin: Int
)
