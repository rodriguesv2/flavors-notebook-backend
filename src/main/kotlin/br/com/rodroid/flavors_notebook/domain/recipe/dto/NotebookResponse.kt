package br.com.rodroid.flavors_notebook.domain.recipe.dto

import java.util.UUID

data class NotebookResponse(
    val id: UUID,
    val name: String,
    val description: String?,
)