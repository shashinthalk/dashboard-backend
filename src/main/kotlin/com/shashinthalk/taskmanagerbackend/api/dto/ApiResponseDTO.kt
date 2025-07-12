package com.shashinthalk.taskmanagerbackend.api.dto

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ApiResponseDTO<T>(
    val status: String,
    val data: T? = null,
    val timestamp: String =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.of("Europe/Vienna"))
            .format(Instant.now()),
)
