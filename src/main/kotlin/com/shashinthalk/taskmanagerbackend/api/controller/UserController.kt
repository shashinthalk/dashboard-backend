package com.shashinthalk.taskmanagerbackend.api.controller

import com.shashinthalk.taskmanagerbackend.data.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private var userRepository: UserRepository,
) {
    @PostMapping("/all")
    fun getAllUsers(): ResponseEntity<Any> {
        return ResponseEntity
            .ok(
                userRepository
                    .findAll(),
            )
    }
}
