package com.shashinthalk.taskmanagerbackend.security.config

import com.shashinthalk.taskmanagerbackend.security.jwt.JwtReactiveAuthenticationManager
import com.shashinthalk.taskmanagerbackend.security.jwt.JwtSecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig(
    private val authenticationManager: JwtReactiveAuthenticationManager,
    private val securityContextRepository: JwtSecurityContextRepository,
) {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/api/auth/validate").permitAll()
                it.anyExchange().authenticated()
            }
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .build()
}
