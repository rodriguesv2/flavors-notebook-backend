package br.com.rodroid.flavors_notebook.domain.auth.dto

data class AuthResponse(
    val token: String,
    val name: String,
    val email: String,
)
