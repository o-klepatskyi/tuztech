package ua.edu.ukma.tuztech.repository

import ua.edu.ukma.tuztech.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>