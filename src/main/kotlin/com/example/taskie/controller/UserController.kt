package com.example.taskie.controller

import com.example.taskie.model.User
import com.example.taskie.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/taskie-api/v1/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/get")
    fun getById(@RequestParam @NotBlank id: String): User {
        return userService.getById(id)
    }

}