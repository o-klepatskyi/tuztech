package ua.edu.ukma.tuztech.service

import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.AddVisitRequest
import ua.edu.ukma.tuztech.dto.EditVisitRequest
import ua.edu.ukma.tuztech.dto.VisitResponse
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
        return VisitResponse(
            id = visit.id,
            barberId = visit.barber.id,
            userId = visit.user.id,
            datetime = visit.datetime,
            durationMin = visit.durationMin
        )
    }
}
