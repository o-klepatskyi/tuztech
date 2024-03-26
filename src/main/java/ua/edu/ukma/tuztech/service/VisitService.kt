package ua.edu.ukma.tuztech.service

import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.*
import ua.edu.ukma.tuztech.entity.Visit
import ua.edu.ukma.tuztech.repository.BarberRepository
import ua.edu.ukma.tuztech.repository.UserRepository
import ua.edu.ukma.tuztech.repository.VisitRepository

@Service
class VisitService(
    private val visitRepository: VisitRepository,
    private val barberRepository: BarberRepository,
    private val userRepository: UserRepository
) {

    fun addVisit(request: AddVisitRequest): VisitResponse {
        val barber = barberRepository.findById(request.barberId)
            .orElseThrow { RuntimeException("Barber not found") }
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User not found") }

        val visit = Visit(
            barber = barber,
            user = user,
            datetime = request.datetime,
            durationMin = request.durationMin
        )

        val savedVisit = visitRepository.save(visit)
        return toVisitResponse(savedVisit)
    }

    fun editVisit(request: EditVisitRequest): VisitResponse {
        visitRepository.findById(request.id)
            .orElseThrow { RuntimeException("Visit not found") }

        val barber = barberRepository.findById(request.barberId)
            .orElseThrow { RuntimeException("Barber not found") }
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User not found") }

        val visit = Visit(
            barber = barber,
            user = user,
            datetime = request.datetime,
            durationMin = request.durationMin
        )

        val updatedVisit = visitRepository.save(visit)
        return toVisitResponse(updatedVisit)
    }

    fun deleteVisit(visitId: Long) {
        if (!visitRepository.existsById(visitId)) {
            throw RuntimeException("Visit not found")
        }
        visitRepository.deleteById(visitId)
    }

    fun getVisitById(visitId: Long): VisitResponse {
        val visit = visitRepository.findById(visitId)
            .orElseThrow { RuntimeException("Visit not found") }
        return toVisitResponse(visit)
    }

    fun getAllVisits(): List<VisitResponse> {
        val visits = visitRepository.findAll()
        return visits.map { toVisitResponse(it) }
    }

    private fun toVisitResponse(visit: Visit): VisitResponse {
        val user = UserResponse(
            id = visit.user.id,
            email = visit.user.email,
            name = visit.user.firstName + " " + visit.user.lastName,
            password = visit.user.password
        )

        val barbershop = BarbershopResponse(
            id = visit.barber.barbershop.id,
            name = visit.barber.barbershop.name,
            address = visit.barber.barbershop.address,
            lat = visit.barber.barbershop.lat,
            lng = visit.barber.barbershop.lng
        )

        val barber = BarberResponse(
            id = visit.barber.id,
            firstName = visit.barber.firstName,
            lastName = visit.barber.lastName
        )

        return VisitResponse(
            id = visit.id,
            barber = barber,
            user = user,
            barbershop = barbershop,
            datetime = visit.datetime,
            durationMin = visit.durationMin
        )
    }
}
