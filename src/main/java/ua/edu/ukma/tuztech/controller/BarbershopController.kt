package ua.edu.ukma.tuztech.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.repository.BarbershopRepository

@RestController
class BarbershopController(
    private val barbershopRepository : BarbershopRepository
) {
    @PostMapping("/barbershop", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody req : AddBarbershopRequest): Any? {
        val barbershop = Barbershop(
            0,
            req.name,
            req.address,
            req.lat,
            req.lng
        )
        barbershopRepository.save(barbershop)
        return barbershop
    }

    @PutMapping("/barbershop/{barbershopId}")
    fun edit(@PathVariable barbershopId : Long, @RequestBody req : EditBarbershopRequest) : Any? {
        val barbershop = Barbershop(
            barbershopId,
            req.name,
            req.address,
            req.lat,
            req.lng
        )
        if (barbershopRepository.findById(barbershopId).isEmpty)
            throw RuntimeException("User not found")
        barbershopRepository.save(barbershop)
        return barbershop
    }

    @DeleteMapping("/barbershop/{barbershopId}")
    fun delete(@PathVariable barbershopId : Long) {
        barbershopRepository.deleteById(barbershopId)
    }

    @GetMapping("/barbershop/{barbershopId}")
    fun getOne(@PathVariable barbershopId: Long): Any? {
        val barbershop = barbershopRepository.findById(barbershopId)
        if (barbershop.isEmpty) throw RuntimeException("User not found")
        return barbershop
    }

    @GetMapping("/barbershop")
    fun getAll(): Any? {
        val users = barbershopRepository.findAll()
        return users
    }

    data class AddBarbershopRequest (
        val name: String,
        val address: String,
        val lat: Double?,
        val lng: Double?
    )

    data class EditBarbershopRequest (
        val name: String,
        val address: String,
        val lat: Double?,
        val lng: Double?
    )
}