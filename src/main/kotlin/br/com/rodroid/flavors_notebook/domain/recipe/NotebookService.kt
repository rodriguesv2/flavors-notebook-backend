package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookResponse
import br.com.rodroid.flavors_notebook.domain.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class NotebookService(
    private val notebookRepository: NotebookRepository,
    private val userRepository: UserRepository,
) {
    fun createNotebook(request: NotebookCreateRequest): NotebookResponse {
        val userEmail = SecurityContextHolder.getContext().authentication?.name ?: ""

        val user = userRepository.findByEmail(userEmail) ?: throw IllegalArgumentException("Usuário não encontrado")

        val newNotebook = Notebook(
            name = request.name,
            description = request.description,
            user = user,
        )

        val savedNotebook = notebookRepository.save(newNotebook)

        return NotebookResponse(
            id = savedNotebook.id!!,
            name = savedNotebook.name,
            description = savedNotebook.description,
        )
    }

    fun listNotebooks(): List<NotebookResponse> {
        val userEmail = SecurityContextHolder.getContext().authentication?.name ?: ""

        val user = userRepository.findAllByEmail(userEmail) ?: throw IllegalArgumentException("Usuário não encontrado")
        val notebooks = notebookRepository.findAllByUserId(user.id!!)

        return notebooks.map {
            NotebookResponse(
                id = it.id!!,
                name = it.name,
                description = it.description,
            )
        }
    }
}