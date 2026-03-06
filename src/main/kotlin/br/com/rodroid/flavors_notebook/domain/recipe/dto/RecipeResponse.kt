package br.com.rodroid.flavors_notebook.domain.recipe.dto

import java.util.UUID

data class RecipeResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val isPublic: Boolean,
    val notebookId: UUID,
    val ingredients: List<RecipeIngredientDto>,
    val utensils: List<String>,
    val steps: List<String>
)