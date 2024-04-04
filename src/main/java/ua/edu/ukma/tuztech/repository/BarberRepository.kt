package ua.edu.ukma.tuztech.repository

import org.springframework.data.jpa.repository.JpaRepository
import ua.edu.ukma.tuztech.entity.Barber

interface BarberRepository : JpaRepository<Barber, Long> {
    fun findAllByBarbershopId(barbershopId: Long): List<Barber>
}