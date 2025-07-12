package com.shashinthalk.taskmanagerbackend.api.controller

import com.shashinthalk.taskmanagerbackend.api.config.UserPublicDTOBuilder
import com.shashinthalk.taskmanagerbackend.api.dto.ApiResponseDTO
import com.shashinthalk.taskmanagerbackend.api.dto.ErrorResponseDTO
import com.shashinthalk.taskmanagerbackend.api.dto.RequestBodyDTO
import com.shashinthalk.taskmanagerbackend.api.dto.TokenResponseDTO
import com.shashinthalk.taskmanagerbackend.api.dto.UserPublicDTO
import com.shashinthalk.taskmanagerbackend.data.repository.UserRepository
import com.shashinthalk.taskmanagerbackend.security.jwt.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@CrossOrigin(origins = ["http://localhost:3001"])
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userPublicDTOBuilder: UserPublicDTOBuilder,
) {
    @PostMapping("/validate")
    fun validateUser(
        @RequestBody requestBodyDTO: RequestBodyDTO,
    ): Mono<ResponseEntity<out ApiResponseDTO<out Any>>> {
        return userRepository.findByEmail(requestBodyDTO.email)
            .flatMap { user ->
                if (passwordEncoder.matches(requestBodyDTO.password, user.password)) {
                    val tokenResponse =
                        TokenResponseDTO(
                            jwtUtil.generateToken(
                                user.email,
                            ),
                        )
                    Mono.just(
                        ResponseEntity.ok(
                            ApiResponseDTO(
                                "valid authorized token",
                                tokenResponse,
                            ),
                        ),
                    )
                } else {
                    Mono.just(
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                            ApiResponseDTO(
                                "unauthorized",
                                ErrorResponseDTO(error = "Invalid password"),
                            ),
                        ),
                    )
                }
            }.switchIfEmpty(
                Mono.just(
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO(
                            "not found",
                            ErrorResponseDTO(
                                "User profile not found with this email",
                            ),
                        ),
                    ),
                ),
            )
    }

    @PostMapping("/get-user")
    fun validateToken(
        @RequestHeader("Authorization") authHeader: String,
    ): Mono<ResponseEntity<ApiResponseDTO<UserPublicDTO>>> {
        val token = authHeader.removePrefix("Bearer ").trim()
        val userEmail = jwtUtil.getEmailFromToken(token)

        return userRepository.findByEmail(userEmail)
            .flatMap { user ->
                userPublicDTOBuilder.buildUserPublicDTO(user)
                    .map { userProfile ->
                        ResponseEntity.status(HttpStatus.OK).body(
                            ApiResponseDTO(
                                "valid user request",
                                userProfile,
                            ),
                        )
                    }
            }
    }
}
