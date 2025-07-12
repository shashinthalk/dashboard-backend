package com.shashinthalk.taskmanagerbackend.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "user_profiles")
data class UserProfile(
    @Id
    val id: String,
    @Column("user_id")
    val userId: Int,
    val fname: String? = null,
    val lname: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zip: String? = null,
    val country: String? = null,
    val website: String? = null,
    val linkedin: String? = null,
    val twitter: String? = null,
    val facebook: String? = null,
    val instagram: String? = null,
    val youtube: String? = null,
    val tiktok: String? = null,
    val pinterest: String? = null,
    val github: String? = null,
    val gitlab: String? = null,
    val bitbucket: String? = null,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
)
