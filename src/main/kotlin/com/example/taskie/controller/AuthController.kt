package com.example.taskie.controller

import com.example.taskie.model.User
import com.example.taskie.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/taskie-api/v1/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/register", consumes = ["multipart/form-data","application/json"])
    fun register(@Valid @RequestBody user: User): ResponseEntity<String> {
        userService.save(user)
        return ResponseEntity.ok("user saved successfully")
    }
}