package br.com.rodroid.flavors_notebook.domain.recipe

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OrderColumn
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "recipes")
class Recipe(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(name = "image_url")
    var imageUrl: String? = null,

    @Column(
        name = "is_public",
        nullable = false
    )
    var isPublic: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "notebook_id",
        nullable = false
    )
    var notebook: Notebook,

    @ElementCollection
    @CollectionTable(
        name = "recipe_ingredients",
        joinColumns = [JoinColumn(name = "recipe_id")]
    )
    var ingredients: MutableList<RecipeIngredient> = mutableListOf(),

    @ElementCollection
    @CollectionTable(
        name = "recipe_utensils",
        joinColumns = [JoinColumn(name = "recipe_id")]
    )
    @Column(name = "utensil_name")
    var utensils: MutableList<String> = mutableListOf(),

    @ElementCollection
    @CollectionTable(
        name = "recipe_steps",
        joinColumns = [JoinColumn(name = "recipe_id")]
    )
    @OrderColumn(name = "step_order")
    @Column(
        name = "step_description",
        columnDefinition = "TEXT"
    )
    var steps: MutableList<String> = mutableListOf(),

    @Column(
        name = "is_favorite",
        nullable = false
    )
    var isFavorite: Boolean = false,

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false
    )
    var createdAt: LocalDateTime = LocalDateTime.now()
)