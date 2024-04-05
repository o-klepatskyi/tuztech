package ua.edu.ukma.tuztech.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import ua.edu.ukma.tuztech.dto.EditUserRequest
import ua.edu.ukma.tuztech.dto.RegisterRequest
import ua.edu.ukma.tuztech.entity.User
import ua.edu.ukma.tuztech.repository.UserRepository
import java.util.*

class UserServiceTest {

    private val userRepository = mock(UserRepository::class.java)

    private val userService = UserService(userRepository)

    @Test
    fun `registerUser should return a User`() {
        val request = RegisterRequest("admin", "admin", "admin@tuz.com", "admin")
        val expectedUser = User(1, "admin", "admin", "admin@tuz.com", "admin")

        `when`(userRepository.save(any(User::class.java))).thenReturn(expectedUser)

        val result = userService.registerUser(request)

        assertNotNull(result)
        assertEquals(expectedUser, result)
    }

    @Test
    fun `editUser should return a User`() {
        val userId = 1L
        val request = EditUserRequest("admin", "admin", "admin@tuz.com", "admin")
        val expectedUser = User(userId, "admin", "admin", "admin@tuz.com", "admin")

        `when`(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser))
        `when`(userRepository.save(any(User::class.java))).thenReturn(expectedUser)

        val result = userService.editUser(userId, request)

        assertNotNull(result)
        assertEquals(expectedUser, result)
    }

    @Test
    fun `editUser should throw an exception if user does not exist`() {
        val userId = 1L
        val request = EditUserRequest("admin", "admin", "admin@tuz.com", "admin")

        `when`(userRepository.findById(userId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { userService.editUser(userId, request) }
    }

    @Test
    fun `deleteUser should not throw an exception if user exists`() {
        val userId = 1L
        `when`(userRepository.existsById(userId)).thenReturn(true)

        assertDoesNotThrow { userService.deleteUser(userId) }
    }

    @Test
    fun `deleteUser should throw an exception if user does not exist`() {
        val userId = 1L
        `when`(userRepository.existsById(userId)).thenReturn(false)

        assertThrows(RuntimeException::class.java) { userService.deleteUser(userId) }
    }

    @Test
    fun `getUserById should return a User if it exists`() {
        val userId = 1L
        val expectedUser = User(userId, "admin", "admin", "admin@tuz.com", "admin")
        `when`(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser))

        val result = userService.getUserById(userId)

        assertNotNull(result)
        assertEquals(expectedUser, result)
    }

    @Test
    fun `getUserById should throw an exception if user does not exist`() {
        val userId = 1L
        `when`(userRepository.findById(userId)).thenReturn(Optional.empty())

        assertThrows(RuntimeException::class.java) { userService.getUserById(userId) }
    }

    @Test
    fun `getUserByEmail should return a User if it exists`() {
        val email = "admin@tuz.com"
        val expectedUser = User(1, "admin", "admin", "admin@tuz.com", "admin")
        `when`(userRepository.findByEmail(email)).thenReturn(expectedUser)

        val result = userService.getUserByEmail(email)

        assertNotNull(result)
        assertEquals(expectedUser, result)
    }

    @Test
    fun `getUserByEmail should return null if user does not exist`() {
        val email = "nonexistent@example.com"
        `when`(userRepository.findByEmail(email)).thenReturn(null)

        val result = userService.getUserByEmail(email)

        assertNull(result)
    }

    @Test
    fun `getAllUsers should return a list of Users`() {
        val users = listOf(User(1, "admin", "admin", "admin@tuz.com", "admin"))
        `when`(userRepository.findAll()).thenReturn(users)

        val result = userService.getAllUsers()

        assertNotNull(result)
        assertEquals(users, result)
    }
}
