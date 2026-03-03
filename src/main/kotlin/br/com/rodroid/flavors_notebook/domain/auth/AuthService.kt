package br.com.rodroid.flavors_notebook.domain.auth

import br.com.rodroid.flavors_notebook.core.security.JwtService
import br.com.rodroid.flavors_notebook.domain.auth.dto.AuthResponse
import br.com.rodroid.flavors_notebook.domain.auth.dto.LoginRequest
import br.com.rodroid.flavors_notebook.domain.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
) {
    fun loginLocal(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("E-mail ou senha incorretos.")

        if (user.password == null) {
            throw IllegalArgumentException("Este usuário utiliza login social. Por favor, faça login com Google ou Facebook.")
        }

        val isPasswordCorrect = passwordEncoder.matches(
            request.password,
            user.password
        )

        if (!isPasswordCorrect) {
            throw IllegalArgumentException("E-mail ou senha incorretos.")
        }

        val token = jwtService.generateToken(user)

        return AuthResponse(
            token = token,
            name = user.name,
            email = user.email,
        )
    }
}