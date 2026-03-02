package br.com.rodroid.flavors_notebook.domain.auth

import br.com.rodroid.flavors_notebook.domain.user.UserService
import br.com.rodroid.flavors_notebook.domain.user.dto.UserCreateRequest
import br.com.rodroid.flavors_notebook.domain.user.dto.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController (
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserResponse> {
        val response = userService.registerLocalUser(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}