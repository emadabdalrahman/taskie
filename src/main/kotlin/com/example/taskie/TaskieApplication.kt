package com.example.taskie

import com.example.taskie.model.User
import com.example.taskie.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.UUID

@SpringBootApplication
class TaskieApplication {
    @Bean
    fun init(userService: UserService) = CommandLineRunner  {
        userService.save(User(
            "1",
            "command line user",
            "command line user",
            "commandlineuser11@gmail.com",
            "99999",
            "admin"
            ))
    }
}

fun main(args: Array<String>) {
    runApplication<TaskieApplication>(*args)
}



