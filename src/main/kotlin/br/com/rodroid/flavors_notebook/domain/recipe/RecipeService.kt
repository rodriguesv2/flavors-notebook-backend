package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.domain.recipe.dto.RecipeCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.RecipeIngredientDto
import br.com.rodroid.flavors_notebook.domain.recipe.dto.RecipeResponse
import br.com.rodroid.flavors_notebook.domain.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val notebookRepository: NotebookRepository,
    private val userRepository: UserRepository,
) {
    fun createRecipe(request: RecipeCreateRequest): RecipeResponse {
        val userEmail = SecurityContextHolder.getContext().authentication?.name ?: ""
        val user = userRepository.findByEmail(userEmail) ?: throw IllegalArgumentException("Usuário não logado")

        val notebook =
            notebookRepository.findById(request.notebookId).orElseThrow { IllegalArgumentException("Caderno não encontrado" )}

        if (notebook.user.id != user.id) {
            throw IllegalArgumentException("Você não tem permissão para criar receitas nesse caderno")
        }

        val ingredientEntities = request.ingredients.map { dto ->
            RecipeIngredient(
                name = dto.name,
                quantity = dto.quantity,
                unit = dto.unit,
            )
        }.toMutableList()

        val newRecipe = Recipe(
            name = request.name,
            description = request.description,
            imageUrl = request.imageUrl,
            notebook = notebook,
            ingredients = ingredientEntities,
            utensils = request.utensils.toMutableList(),
            steps = request.steps.toMutableList(),
        )

        val savedRecipe = recipeRepository.save(newRecipe)

        return RecipeResponse(
            id = savedRecipe.id!!,
            name = savedRecipe.name,
            description = savedRecipe.description,
            imageUrl = savedRecipe.imageUrl,
            isPublic = savedRecipe.isPublic,
            notebookId = savedRecipe.notebook.id!!,
            ingredients = savedRecipe.ingredients.map { RecipeIngredientDto(it.name, it.quantity, it.unit) },
            utensils = savedRecipe.utensils,
            steps = savedRecipe.steps,
        )
    }
}