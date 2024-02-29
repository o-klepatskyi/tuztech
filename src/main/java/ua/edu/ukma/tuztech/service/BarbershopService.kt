package ua.edu.ukma.tuztech.service

import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.AddBarbershopRequest
import ua.edu.ukma.tuztech.dto.EditBarbershopRequest
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.repository.BarbershopRepository

@Service
class BarbershopService(private val barbershopRepository: BarbershopRepository) {

    fun registerBarbershop(request: AddBarbershopRequest): Barbershop {
        val barbershop = Barbershop(
            name = request.name,
            address = request.address,
            lat = request.lat,
            lng = request.lng
        )
        return barbershopRepository.save(barbershop)
    }

    fun editBarbershop(barbershopId: Long, request: EditBarbershopRequest): Barbershop {
        val barbershop = Barbershop(
            barbershopId,
            request.name,
            request.address,
            request.lat,
            request.lng
        )
        if (barbershopRepository.findById(barbershopId).isEmpty)
            throw RuntimeException("User not found")
        return barbershopRepository.save(barbershop)
    }

    fun deleteBarbershop(barbershopId: Long) {
        if (!barbershopRepository.existsById(barbershopId)) {
            throw RuntimeException("Barbershop not found")
        }
        barbershopRepository.deleteById(barbershopId)
    }

    fun getBarbershopById(barbershopId: Long): Barbershop {
        return barbershopRepository.findById(barbershopId)
            .orElseThrow { RuntimeException("Barbershop not found") }
    }

    fun getAllBarbershops(): List<Barbershop> {
        return barbershopRepository.findAll()
    }
}