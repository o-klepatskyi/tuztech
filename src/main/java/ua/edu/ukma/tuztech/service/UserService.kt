package ua.edu.ukma.tuztech.service

import org.springframework.stereotype.Service
import ua.edu.ukma.tuztech.dto.EditUserRequest
import ua.edu.ukma.tuztech.dto.RegisterRequest
import ua.edu.ukma.tuztech.entity.User
import ua.edu.ukma.tuztech.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    fun registerUser(request: RegisterRequest): User {
        val user = User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = request.password
        )
        return userRepository.save(user)
    }

    fun editUser(userId: Long, request: EditUserRequest): User {
        if (userRepository.findById(userId).isEmpty)
            throw RuntimeException("User not found")
        val user = User(
            userId,
            request.firstName,
            request.lastName,
            request.email,
            request.password
        )
        return userRepository.save(user)
    }

    fun deleteUser(userId: Long) {
        if (!userRepository.existsById(userId)) {
            throw RuntimeException("User not found")
        }
        userRepository.deleteById(userId)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found") }
    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}