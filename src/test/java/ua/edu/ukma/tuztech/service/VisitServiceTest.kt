package ua.edu.ukma.tuztech.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import ua.edu.ukma.tuztech.dto.AddVisitRequest
import ua.edu.ukma.tuztech.dto.EditVisitRequest
import ua.edu.ukma.tuztech.entity.Barber
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.entity.User
import ua.edu.ukma.tuztech.entity.Visit
import ua.edu.ukma.tuztech.repository.BarberRepository
import ua.edu.ukma.tuztech.repository.UserRepository
import ua.edu.ukma.tuztech.repository.VisitRepository
import java.time.LocalDateTime
import java.util.*

class VisitServiceTest {

    private val visitRepository = mock(VisitRepository::class.java)
    private val barberRepository = mock(BarberRepository::class.java)
    private val userRepository = mock(UserRepository::class.java)

    private val visitService = VisitService(visitRepository, barberRepository, userRepository)

    @Test
    fun `addVisit should return a VisitResponse`() {
        val request = AddVisitRequest(1, 1, LocalDateTime.now(), 30)
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val barber = Barber(1, "first name", "last name", barbershop, "barber image")
        val user = User(1, "admin", "admin", "admin@tuz.com", "admin")
        val expectedVisit = Visit(1, barber, user, LocalDateTime.now(), 30)

        `when`(barberRepository.findById(request.barberId)).thenReturn(Optional.of(barber))
        `when`(userRepository.findById(request.userId)).thenReturn(Optional.of(user))
        `when`(visitRepository.save(any(Visit::class.java))).thenReturn(expectedVisit)

        val result = visitService.addVisit(request)

        assertNotNull(result)
    }

    @Test
    fun `editVisit should return a VisitResponse`() {
        val request = EditVisitRequest(1, 1, 1, LocalDateTime.now().plusDays(1), 60)
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val barber = Barber(1, "first name", "last name", barbershop, "barber image")
        val user = User(1, "admin", "admin", "admin@tuz.com", "admin")
        val visit = Visit(1, barber, user, LocalDateTime.now(), 30)
        val expectedVisit = Visit(1, barber, user, LocalDateTime.now().plusDays(1), 60)

        `when`(visitRepository.findById(request.id)).thenReturn(Optional.of(visit))
        `when`(barberRepository.findById(request.barberId)).thenReturn(Optional.of(barber))
        `when`(userRepository.findById(request.userId)).thenReturn(Optional.of(user))
        `when`(visitRepository.save(any(Visit::class.java))).thenReturn(expectedVisit)

        val result = visitService.editVisit(request)

        assertNotNull(result)
    }

    @Test
    fun `editVisit should throw an exception if visit does not exist`() {
        val request = EditVisitRequest(1, 1, 1, LocalDateTime.now().plusDays(1), 60)
        `when`(visitRepository.findById(request.id)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { visitService.editVisit(request) }
    }

    @Test
    fun `deleteVisit should not throw an exception if visit exists`() {
        val visitId = 1L
        `when`(visitRepository.existsById(visitId)).thenReturn(true)

        assertDoesNotThrow { visitService.deleteVisit(visitId) }
    }

    @Test
    fun `deleteVisit should throw an exception if visit does not exist`() {
        val visitId = 1L
        `when`(visitRepository.existsById(visitId)).thenReturn(false)

        assertThrows(RuntimeException::class.java) { visitService.deleteVisit(visitId) }
    }

    @Test
    fun `getVisitById should return a VisitResponse if visit exists`() {
        val visitId = 1L
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val barber = Barber(1, "first name", "last name", barbershop, "barber image")
        val user = User(1, "admin", "admin", "admin@tuz.com", "admin")
        val expectedVisit = Visit(visitId, barber, user, LocalDateTime.now(), 30)
        `when`(visitRepository.findById(visitId)).thenReturn(Optional.of(expectedVisit))

        val result = visitService.getVisitById(visitId)

        assertNotNull(result)
    }

    @Test
    fun `getVisitById should throw an exception if visit does not exist`() {
        val visitId = 1L
        `when`(visitRepository.findById(visitId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { visitService.getVisitById(visitId) }
    }

    @Test
    fun `getAllVisits should return a list of VisitResponses`() {
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val barber = Barber(1, "first name", "last name", barbershop, "barber image")
        val user = User(1, "admin", "admin", "admin@tuz.com", "admin")
        val visits = listOf(Visit(1, barber, user, LocalDateTime.now(), 30))
        `when`(visitRepository.findAll()).thenReturn(visits)

        val result = visitService.getAllVisits()

        assertNotNull(result)
    }
}