package br.com.rodroid.flavors_notebook.domain.recipe.dto

data class NotebookUpdateRequest(
    val name: String? = null,
    val description: String? = null,
)