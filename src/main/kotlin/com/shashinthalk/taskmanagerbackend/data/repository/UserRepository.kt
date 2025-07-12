package com.shashinthalk.taskmanagerbackend.data.repository

import com.shashinthalk.taskmanagerbackend.data.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, Long> {
    fun findByEmail(email: String): Mono<User>
}
