package ua.edu.ukma.tuztech.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import ua.edu.ukma.tuztech.dto.AddBarbershopRequest
import ua.edu.ukma.tuztech.dto.EditBarbershopRequest
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.repository.BarbershopRepository
import java.util.*

class BarbershopServiceTest {

    private val barbershopRepository = mock(BarbershopRepository::class.java)

    private val barbershopService = BarbershopService(barbershopRepository)

    @Test
    fun `registerBarbershop should return a Barbershop`() {
        val request = AddBarbershopRequest("name", "address", 1.0, 1.0,"barbershop image")
        val expectedBarbershop = Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image")

        `when`(barbershopRepository.save(any(Barbershop::class.java))).thenReturn(expectedBarbershop)

        val result = barbershopService.registerBarbershop(request)

        assertNotNull(result)
        assertEquals(expectedBarbershop, result)
    }

    @Test
    fun `editBarbershop should return a Barbershop`() {
        val barbershopId = 1L
        val request = EditBarbershopRequest("another name", "another address", 2.0, 2.0,"another barbershop image")
        val expectedBarbershop = Barbershop(barbershopId, "name", "address", 1.0, 1.0,"barbershop image")

        `when`(barbershopRepository.findById(barbershopId)).thenReturn(Optional.of(expectedBarbershop))
        `when`(barbershopRepository.save(any(Barbershop::class.java))).thenReturn(expectedBarbershop)

        val result = barbershopService.editBarbershop(barbershopId, request)

        assertNotNull(result)
        assertEquals(expectedBarbershop, result)
    }

    @Test
    fun `editBarbershop should throw an exception if barbershop does not exist`() {
        val barbershopId = 1L
        val request = EditBarbershopRequest("another name", "another address", 2.0, 2.0,"another barbershop image")

        `when`(barbershopRepository.findById(barbershopId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { barbershopService.editBarbershop(barbershopId, request) }
    }

    @Test
    fun `deleteBarbershop should not throw an exception if barbershop exists`() {
        val barbershopId = 1L
        `when`(barbershopRepository.existsById(barbershopId)).thenReturn(true)

        assertDoesNotThrow { barbershopService.deleteBarbershop(barbershopId) }
    }

    @Test
    fun `deleteBarbershop should throw an exception if barbershop does not exist`() {
        val barbershopId = 1L
        `when`(barbershopRepository.existsById(barbershopId)).thenReturn(false)

        assertThrows(RuntimeException::class.java) { barbershopService.deleteBarbershop(barbershopId) }
    }

    @Test
    fun `getBarbershopById should return a Barbershop if it exists`() {
        val barbershopId = 1L
        val expectedBarbershop = Barbershop(barbershopId, "name", "address", 1.0, 1.0,"barbershop image")
        `when`(barbershopRepository.findById(barbershopId)).thenReturn(Optional.of(expectedBarbershop))

        val result = barbershopService.getBarbershopById(barbershopId)

        assertNotNull(result)
        assertEquals(expectedBarbershop, result)
    }

    @Test
    fun `getBarbershopById should throw an exception if barbershop does not exist`() {
        val barbershopId = 1L
        `when`(barbershopRepository.findById(barbershopId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { barbershopService.getBarbershopById(barbershopId) }
    }

    @Test
    fun `getAllBarbershops should return a list of Barbershops`() {
        val barbershops = listOf(Barbershop(1, "name", "address", 1.0, 1.0,"barbershop image"))
        `when`(barbershopRepository.findAll()).thenReturn(barbershops)

        val result = barbershopService.getAllBarbershops()

        assertNotNull(result)
        assertEquals(barbershops, result)
    }
}
