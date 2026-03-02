package br.com.rodroid.flavors_notebook.domain.user

import br.com.rodroid.flavors_notebook.domain.user.dto.UserCreateRequest
import br.com.rodroid.flavors_notebook.domain.user.dto.UserResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun registerLocalUser(request: UserCreateRequest): UserResponse {
        if (userRepository.findByEmail(request.email) != null) {
            throw IllegalArgumentException("E-mail já cadastrado")
        }

        val newUser = User(
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            authProvider = AuthProvider.LOCAL
        )

        val savedUser = userRepository.save(newUser)

        return UserResponse(
            id = savedUser.id!!,
            name = savedUser.name,
            email = savedUser.email,
            authProvider = savedUser.authProvider
        )
    }
}