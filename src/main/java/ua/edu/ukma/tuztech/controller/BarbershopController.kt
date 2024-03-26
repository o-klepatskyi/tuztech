package ua.edu.ukma.tuztech.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.dto.AddBarbershopRequest
import ua.edu.ukma.tuztech.dto.EditBarbershopRequest
import ua.edu.ukma.tuztech.entity.Barbershop
import ua.edu.ukma.tuztech.service.BarbershopService

@RestController
class BarbershopController(
    private val barbershopService: BarbershopService
) {
    @PostMapping(
        "/barbershop",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun register(@RequestBody req: AddBarbershopRequest): Barbershop {
        return barbershopService.registerBarbershop(req)
    }

    @PutMapping("/barbershop/{barbershopId}")
    fun edit(@PathVariable barbershopId: Long, @RequestBody req: EditBarbershopRequest): Barbershop {
        return barbershopService.editBarbershop(barbershopId, req)
    }

    @DeleteMapping("/barbershop/{barbershopId}")
    fun delete(@PathVariable barbershopId: Long) {
        barbershopService.deleteBarbershop(barbershopId)
    }

    @GetMapping("/barbershop/{barbershopId}")
    fun getOne(@PathVariable barbershopId: Long): Barbershop {
        return barbershopService.getBarbershopById(barbershopId)
    }

    @GetMapping("/barbershop")
    fun getAll(): List<Barbershop> {
        return barbershopService.getAllBarbershops()
    }
}