package br.com.rodroid.flavors_notebook.domain.recipe

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NotebookRepository: JpaRepository<Notebook, UUID> {
    fun findAllByUserId(userId: UUID): List<Notebook>
}