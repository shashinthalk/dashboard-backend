package com.shashinthalk.taskmanagerbackend.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "users")
data class User(
    @Id
    val id: String,
    val email: String,
    val password: String,
    val isActive: Boolean,
    val role: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
