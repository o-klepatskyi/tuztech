package ua.edu.ukma.tuztech.repository

import ua.edu.ukma.tuztech.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long>