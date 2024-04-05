package ua.edu.ukma.tuztech.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import ua.edu.ukma.tuztech.dto.AddBarberRequest
import ua.edu.ukma.tuztech.dto.EditBarberRequest
import ua.edu.ukma.tuztech.entity.Barber
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.repository.BarberRepository
import ua.edu.ukma.tuztech.repository.BarbershopRepository
import java.util.*

class BarberServiceTest {

    private val barberRepository = mock(BarberRepository::class.java)
    private val barbershopRepository = mock(BarbershopRepository::class.java)

    private val barberService = BarberService(barberRepository, barbershopRepository)

    @Test
    fun `registerBarber should return a Barber`() {
        val request = AddBarberRequest("first name", "last name", 1,"barber image")
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val expectedBarber = Barber(1, "first name", "last name", barbershop, "barber image")

        `when`(barbershopRepository.findById(request.barbershopId)).thenReturn(Optional.of(barbershop))
        `when`(barberRepository.save(any(Barber::class.java))).thenReturn(expectedBarber)

        val result = barberService.registerBarber(request)

        assertNotNull(result)
        assertEquals(expectedBarber, result)
    }

    @Test
    fun `editBarber should return a Barber`() {
        val barberId = 1L
        val request = EditBarberRequest("first name", "last name", 1,"barber image")
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val savedBarber = Barber(barberId, "any name", "any name", barbershop, "any image")
        val expectedBarber = Barber(barberId, "first name", "last name", barbershop, "barber image")

        `when`(barberRepository.findById(barberId)).thenReturn(Optional.of(savedBarber))
        `when`(barbershopRepository.findById(request.barbershopId)).thenReturn(Optional.of(barbershop))
        `when`(barberRepository.save(any(Barber::class.java))).thenReturn(expectedBarber)

        val result = barberService.editBarber(barberId, request)

        assertNotNull(result)
        assertEquals(expectedBarber, result)
    }

    @Test
    fun `deleteBarber should not throw an exception if barber exists`() {
        val barberId = 1L
        `when`(barberRepository.existsById(barberId)).thenReturn(true)

        assertDoesNotThrow { barberService.deleteBarber(barberId) }
    }

    @Test
    fun `deleteBarber should throw an exception if barber does not exist`() {
        val barberId = 1L
        `when`(barberRepository.existsById(barberId)).thenReturn(false)

        assertThrows(RuntimeException::class.java) { barberService.deleteBarber(barberId) }
    }

    @Test
    fun `getBarberById should return a Barber if it exists`() {
        val barberId = 1L
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val expectedBarber = Barber(barberId, "first name", "last name", barbershop, "barber image")
        `when`(barberRepository.findById(barberId)).thenReturn(Optional.of(expectedBarber))

        val result = barberService.getBarberById(barberId)

        assertNotNull(result)
        assertEquals(expectedBarber, result)
    }

    @Test
    fun `getBarberById should throw an exception if barber does not exist`() {
        val barberId = 1L
        `when`(barberRepository.findById(barberId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { barberService.getBarberById(barberId) }
    }

    @Test
    fun `getAllBarbersByBarbershopId should return a list of Barbers`() {
        val barbershopId = 1L
        val barbershop = Barbershop(barbershopId, "name", "address", 1.0, 1.0,"barbershop image")
        val barbers = listOf(Barber(1, "first name", "last name", barbershop, "barber image"))
        `when`(barberRepository.findAllByBarbershopId(barbershopId)).thenReturn(barbers)

        val result = barberService.getAllBarbersByBarbershopId(barbershopId)

        assertNotNull(result)
        assertEquals(barbers, result)
    }

    @Test
    fun `getAllBarbers should return a list of Barbers`() {
        val barbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")
        val barbers = listOf(Barber(1, "first name", "last name", barbershop, "barber image"))
        `when`(barberRepository.findAll()).thenReturn(barbers)

        val result = barberService.getAllBarbers()

        assertNotNull(result)
        assertEquals(barbers, result)
    }
}
