package ua.edu.ukma.tuztech.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = ua.edu.ukma.tuztech.entity.User
@Service
class UserDetailsService(
        private val userService: UserService
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
            userService.getUserByEmail(username)?.mapToUserDetails() ?:
            throw UsernameNotFoundException("User with email $username not found")
    private fun ApplicationUser.mapToUserDetails(): UserDetails = User
            .builder()
            .username(this.email)
            .password(this.password)
            .build()
}

