package com.shashinthalk.taskmanagerbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Handle errors from REST endpoints and cleans them up for the frontend.
 * All the error messages by field, so they're easy to display.
 */
@RestControllerAdvice
class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, List<String>>> {
        fun camelToSnake(name: String): String = name.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
        val errors =
            ex.bindingResult.fieldErrors
                .groupBy { camelToSnake(it.field) }
                .mapValues { entry -> entry.value.map { it.defaultMessage ?: "Bad Request" } }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
