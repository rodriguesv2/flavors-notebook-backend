package br.com.rodroid.flavors_notebook.domain.recipe

import jakarta.persistence.Embeddable

@Embeddable
class RecipeIngredient(
    var name: String = "",
    var quantity: String? = "",
    var unit: String? = "",
)