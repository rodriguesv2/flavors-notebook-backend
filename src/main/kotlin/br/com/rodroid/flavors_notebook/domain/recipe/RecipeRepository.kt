package br.com.rodroid.flavors_notebook.domain.recipe

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RecipeRepository: JpaRepository<Recipe, UUID> {
    fun findAllByNotebookId(notebookId: UUID): List<Recipe>
}