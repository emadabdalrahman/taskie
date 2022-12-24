package com.example.taskie.security

import com.example.taskie.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun authenticate(authentication: Authentication?): Authentication {
        val email = authentication?.name
        val password = authentication?.credentials as String?
        val user = userService.loadUserByUsername(email)
        if (password == null) throw BadCredentialsException("Bad Credentials")
        if (passwordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(email, password, user.authorities)
        } else {
            throw BadCredentialsException("Wrong Password")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.equals(UsernamePasswordAuthenticationToken::class.java) ?: false
    }
}