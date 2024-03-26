package ua.edu.ukma.tuztech.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ua.edu.ukma.tuztech.dto.EditUserRequest
import ua.edu.ukma.tuztech.dto.RegisterRequest
import ua.edu.ukma.tuztech.entity.User
import ua.edu.ukma.tuztech.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping(
        "/user/register",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun register(@RequestBody req: RegisterRequest): User {
        return userService.registerUser(req)
    }

    @PutMapping("/user/{userId}")
    fun edit(@PathVariable userId: Long, @RequestBody req: EditUserRequest): User {
        return userService.editUser(userId, req)
    }

    @DeleteMapping("/user/{userId}")
    fun delete(@PathVariable userId: Long) {
        userService.deleteUser(userId)
    }

    @GetMapping("/user/{userId}")
    fun getOne(@PathVariable userId: Long): User {
        return userService.getUserById(userId)
    }

    @GetMapping("/user")
    fun getAll(): List<User> {
        return userService.getAllUsers()
    }
}