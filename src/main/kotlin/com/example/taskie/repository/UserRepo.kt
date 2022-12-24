package com.example.taskie.repository

import com.example.taskie.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User, String> {
    fun findUsersByEmail(email: String): User?
}