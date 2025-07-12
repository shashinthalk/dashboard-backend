package com.shashinthalk.taskmanagerbackend.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtSecurityContextRepository(
    private val authenticationManager: JwtReactiveAuthenticationManager,
) :
    ServerSecurityContextRepository {
    override fun save(
        exchange: ServerWebExchange,
        context: SecurityContext,
    ): Mono<Void> = Mono.empty()

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        val authHeader = exchange.request.headers.getFirst("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val authToken = authHeader.substring(7)
            val auth = UsernamePasswordAuthenticationToken(authToken, authToken)
            return authenticationManager.authenticate(auth)
                .map { SecurityContextImpl(it) }
        }
        return Mono.empty()
    }
}
