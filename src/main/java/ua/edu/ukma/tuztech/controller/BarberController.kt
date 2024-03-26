package ua.edu.ukma.tuztech.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.dto.AddBarberRequest
import ua.edu.ukma.tuztech.dto.EditBarberRequest
import ua.edu.ukma.tuztech.entity.Barber
import ua.edu.ukma.tuztech.service.BarberService

@RestController
class BarberController(private val barberService: BarberService) {

    @PostMapping(
        "/barber",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun register(@RequestBody req: AddBarberRequest): Barber {
        return barberService.registerBarber(req)
    }

    @PutMapping("/barber/{barberId}")
    fun edit(@PathVariable barberId: Long, @RequestBody req: EditBarberRequest): Barber{
        return barberService.editBarber(barberId, req)
    }

    @DeleteMapping("/barber/{barberId}")
    fun delete(@PathVariable barberId: Long) {
        barberService.deleteBarber(barberId)
    }

    @GetMapping("/barber/{barberId}")
    fun getOne(@PathVariable barberId: Long): Barber {
        return barberService.getBarberById(barberId)
    }

    @GetMapping("/barber")
    fun getAll(): List<Barber> {
        return barberService.getAllBarbers()
    }
}
