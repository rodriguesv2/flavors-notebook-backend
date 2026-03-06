package br.com.rodroid.flavors_notebook.domain.recipe.dto

import jakarta.validation.constraints.NotBlank

data class RecipeIngredientDto(
    @field:NotBlank(message = "O nome do ingrediente é obrigatório.")
    val name: String,
    val quantity: String? = "",
    val unit: String? = "",
)
