package br.com.rodroid.flavors_notebook.domain.recipe.dto

import jakarta.validation.constraints.NotBlank

class NotebookCreateRequest(
    @field:NotBlank(message = "O nome do caderno é obrigatório.")
    val name: String,
    val description: String? = null,
)

