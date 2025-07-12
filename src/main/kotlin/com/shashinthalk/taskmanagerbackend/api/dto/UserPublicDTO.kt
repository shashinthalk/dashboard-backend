package com.shashinthalk.taskmanagerbackend.api.dto

import java.time.Instant

class UserPublicDTO(
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
    val isActive: Boolean,
    val role: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
