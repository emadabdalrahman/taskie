package com.example.taskie.service

import com.example.taskie.model.User
import com.example.taskie.repository.UserRepo
import com.example.taskie.security.UserDetailsImpl
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Lazy val userRepo: UserRepo, @Lazy val encoder: BCryptPasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepo.findUsersByEmail(email ?: "")
            ?: throw UsernameNotFoundException("can not find $email")
        return UserDetailsImpl(user)
    }

    fun getById(id: String): User {
        return userRepo.getReferenceById(id)
    }

    fun save(user: User) {
        user.password = encoder.encode(user.password)
        userRepo.saveAndFlush(user);
    }

}