package com.shashinthalk.taskmanagerbackend.data.repository

import com.shashinthalk.taskmanagerbackend.data.model.UserProfile
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserProfileRepository : ReactiveCrudRepository<UserProfile, Int> {
    fun findByUserId(userId: Int): Mono<UserProfile>
}
