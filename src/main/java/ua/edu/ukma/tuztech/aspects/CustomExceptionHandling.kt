package ua.edu.ukma.tuztech.aspects

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import ua.edu.ukma.tuztech.exception.ExceptionMessage
import java.time.LocalDateTime

@ControllerAdvice
class CustomExceptionHandling {
    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(ex: ExpiredJwtException, request: WebRequest): ResponseEntity<String> {
        val uri = (request as ServletWebRequest).request.requestURI
        val exceptionMessage = ExceptionMessage(ex.message, uri, LocalDateTime.now().toString())
        return ResponseEntity(ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException::class)
    fun handleAccessDeniedException(ex: org.springframework.security.access.AccessDeniedException, request: WebRequest): ResponseEntity<String> {
        val uri = (request as ServletWebRequest).request.requestURI
        val exceptionMessage = ExceptionMessage("${ex.message}, Probably user doesn't have enough permissions to call the resource", uri, LocalDateTime.now().toString())
        return ResponseEntity(ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherException(ex: Exception, request: WebRequest): ResponseEntity<String> {
        val uri = (request as ServletWebRequest).request.requestURI
        val exceptionMessage = ExceptionMessage(ex.message, uri, LocalDateTime.now().toString())
        ex.printStackTrace()
        return ResponseEntity(ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.BAD_REQUEST)
    }
}