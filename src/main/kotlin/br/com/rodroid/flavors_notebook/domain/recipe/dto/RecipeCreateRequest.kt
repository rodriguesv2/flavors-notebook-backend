package br.com.rodroid.flavors_notebook.domain.recipe.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class RecipeCreateRequest(
    @field:NotBlank(message = "O nome da receita é obrigatório.")
    val name: String,

    val description: String? = null,
    val imageUrl: String? = null,

    @field:NotNull(message = "O ID do caderno é obrigatório.")
    val notebookId: UUID,

    @field:Valid
    val ingredients: List<RecipeIngredientDto> = emptyList(),
    val utensils: List<String> = emptyList(),

    @field:NotEmpty(message = "A receira precisa ter pelo menos um passo")
    val steps: List<String>,
)
