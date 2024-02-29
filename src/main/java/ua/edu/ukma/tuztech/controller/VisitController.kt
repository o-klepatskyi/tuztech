package ua.edu.ukma.tuztech.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.dto.AddVisitRequest
import ua.edu.ukma.tuztech.dto.EditVisitRequest
import ua.edu.ukma.tuztech.dto.VisitResponse
import ua.edu.ukma.tuztech.service.VisitService

@RestController
class VisitController(private val visitService: VisitService) {

    @PostMapping(
        "/visit/register",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addVisit(@RequestBody request: AddVisitRequest): ResponseEntity<VisitResponse> {
        val visit = visitService.addVisit(request)
        return ResponseEntity(visit, HttpStatus.CREATED)
    }

    @PutMapping(
        "/visit/{visitId}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun editVisit(@PathVariable visitId: Long, @RequestBody request: EditVisitRequest): ResponseEntity<VisitResponse> {
        val visit = visitService.editVisit(request.copy(id = visitId))
        return ResponseEntity(visit, HttpStatus.OK)
    }

    @DeleteMapping("/visit/{visitId}")
    fun deleteVisit(@PathVariable visitId: Long): ResponseEntity<Void> {
        visitService.deleteVisit(visitId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/visit/{visitId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getVisitById(@PathVariable visitId: Long): ResponseEntity<VisitResponse> {
        val visit = visitService.getVisitById(visitId)
        return ResponseEntity(visit, HttpStatus.OK)
    }

    @GetMapping("/visit", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllVisits(): ResponseEntity<List<VisitResponse>> {
        val visits = visitService.getAllVisits()
        return ResponseEntity(visits, HttpStatus.OK)
    }
}
