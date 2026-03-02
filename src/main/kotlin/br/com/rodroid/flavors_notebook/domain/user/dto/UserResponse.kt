package br.com.rodroid.flavors_notebook.domain.user.dto

import br.com.rodroid.flavors_notebook.domain.user.AuthProvider
import java.util.UUID

data class UserResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val authProvider: AuthProvider,
)
