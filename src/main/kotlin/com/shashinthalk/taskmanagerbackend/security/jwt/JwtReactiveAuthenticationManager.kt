package com.shashinthalk.taskmanagerbackend.security.jwt

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtReactiveAuthenticationManager(
    private val jwtUtil: JwtUtil,
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials as? String ?: return Mono.empty()
        return if (jwtUtil.validateToken(token)) {
            val username = jwtUtil.getEmailFromToken(token)
            Mono.just(UsernamePasswordAuthenticationToken(username, token, emptyList()))
        } else {
            Mono.empty()
        }
    }
}
