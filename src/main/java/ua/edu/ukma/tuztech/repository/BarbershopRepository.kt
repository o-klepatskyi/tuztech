package ua.edu.ukma.tuztech.repository

import org.springframework.data.repository.CrudRepository
import ua.edu.ukma.tuztech.entity.Barbershop

interface BarbershopRepository : CrudRepository<Barbershop, Long>
