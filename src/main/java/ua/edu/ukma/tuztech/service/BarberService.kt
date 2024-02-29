package ua.edu.ukma.tuztech.service

import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.AddBarberRequest
import ua.edu.ukma.tuztech.dto.EditBarberRequest
import ua.edu.ukma.tuztech.entity.Barber
import ua.edu.ukma.tuztech.repository.BarberRepository
import ua.edu.ukma.tuztech.repository.BarbershopRepository

@Service
class BarberService(
    private val barberRepository: BarberRepository,
    private val barbershopRepository: BarbershopRepository
) {

    fun registerBarber(request: AddBarberRequest): Barber {
        val barbershop = barbershopRepository.findById(request.barbershopId)
            .orElseThrow { RuntimeException("Barbershop not found") }

        val barber = Barber(
            firstName = request.firstName,
            lastName = request.lastName,
            barbershop = barbershop,
        )
        return barberRepository.save(barber)
    }

    fun editBarber(barberId: Long, request: EditBarberRequest): Barber {
        barberRepository.findById(barberId)
            .orElseThrow { RuntimeException("Barber not found") }
        val barbershop = barbershopRepository.findById(request.barbershopId)
            .orElseThrow { RuntimeException("Barbershop not found") }

        val barber = Barber(
            barberId,
            request.firstName,
            request.lastName,
            barbershop,
        )
        return barberRepository.save(barber)
    }

    fun deleteBarber(barberId: Long) {
        if (!barberRepository.existsById(barberId)) {
            throw RuntimeException("Barber not found")
        }
        barberRepository.deleteById(barberId)
    }

    fun getBarberById(barberId: Long): Barber {
        return barberRepository.findById(barberId)
            .orElseThrow { RuntimeException("Barber not found") }
    }

    fun getAllBarbers(): List<Barber> {
        return barberRepository.findAll()
    }
}