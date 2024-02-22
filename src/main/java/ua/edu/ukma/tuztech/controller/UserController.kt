package ua.edu.ukma.tuztech.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.entity.User
import ua.edu.ukma.tuztech.repository.UserRepository

@RestController
class UserController(
    private val userRepository : UserRepository
) {
    @PostMapping("/user/register", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody req : RegisterRequest): Any? {
        val user = User(
            0,
            req.firstName,
            req.lastName,
            req.email,
            req.password
        )
        userRepository.save(user)
        return user
    }

    @PutMapping("/user/{userId}")
    fun edit(@PathVariable userId : Long, @RequestBody req : EditUserRequest) : Any? {
        val user = User(
            userId,
            req.firstName,
            req.lastName,
            req.email,
            req.password
        )
        if (userRepository.findById(userId).isEmpty)
            throw RuntimeException("User not found")
        userRepository.save(user)
        return user
    }

    @DeleteMapping("/user/{userId}")
    fun delete(@PathVariable userId : Long) {
        userRepository.deleteById(userId)
    }

    @GetMapping("/user/{userId}")
    fun getOne(@PathVariable userId: Long): Any? {
        val user = userRepository.findById(userId)
        if (user.isEmpty) throw RuntimeException("User not found")
        return user
    }

    @GetMapping("/user")
    fun getAll(): Any? {
        val users = userRepository.findAll()
        return users
    }

    data class RegisterRequest (
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
    )

    data class EditUserRequest (
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
    )
}