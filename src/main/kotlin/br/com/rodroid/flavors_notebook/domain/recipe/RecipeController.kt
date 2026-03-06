package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.domain.recipe.dto.RecipeCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.RecipeResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipes")
class RecipeController(
    private val recipeService: RecipeService
) {

    @PostMapping
    fun create(
        @Valid @RequestBody request: RecipeCreateRequest
    ): ResponseEntity<RecipeResponse> {
        val response = recipeService.createRecipe(request)
        return ResponseEntity.ok(response)
    }
}