package ua.edu.ukma.tuztech.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import ua.edu.ukma.tuztech.entity.User

interface UserRepository : CrudRepository<User, Long>