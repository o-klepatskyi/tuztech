package ua.edu.ukma.tuztech.repository

import org.springframework.data.jpa.repository.JpaRepository
import ua.edu.ukma.tuztech.entity.Visit

interface VisitRepository : JpaRepository<Visit, Long>
