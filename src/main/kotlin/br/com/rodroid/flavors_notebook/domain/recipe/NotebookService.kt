package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.core.exception.ResourceNotFoundException
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookResponse
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookUpdateRequest
import br.com.rodroid.flavors_notebook.domain.user.User
import br.com.rodroid.flavors_notebook.domain.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NotebookService(
    private val notebookRepository: NotebookRepository,
    private val userRepository: UserRepository,
) {
    fun createNotebook(request: NotebookCreateRequest): NotebookResponse {
        val user = getUser()

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
        val user = getUser()
        val notebooks = notebookRepository.findAllByUserId(user.id!!)

        return notebooks.map {
            NotebookResponse(
                id = it.id!!,
                name = it.name,
                description = it.description,
            )
        }
    }

    fun getNotebook(id: UUID): NotebookResponse {
        val notebook = getNotebookById(id)

        if (notebook.user.id != getUser().id) {
            throw IllegalArgumentException("Você não tem permissão para acessar esse caderno")
        }

        return NotebookResponse(
            id = notebook.id!!,
            name = notebook.name,
            description = notebook.description,
        )
    }

    fun updateNotebook(
        id: UUID,
        request: NotebookUpdateRequest
    ): NotebookResponse {
        val notebook = getNotebookById(id)

        if (notebook.user.id != getUser().id) {
            throw IllegalArgumentException("Você não tem permissão para atualizar esse caderno")
        }

        val newNotebook = notebook.copy(
            name = request.name ?: notebook.name,
            description = request.description ?: notebook.description,
        )

        val savedNotebook = notebookRepository.save(newNotebook)

        return NotebookResponse(
            id = savedNotebook.id!!,
            name = savedNotebook.name,
            description = savedNotebook.description,
        )
    }

    fun deleteNotebook(
        id: UUID
    ) {
        val notebook = getNotebookById(id)

        if (notebook.user.id != getUser().id) {
            throw IllegalArgumentException("Você não tem permissão para deletar esse caderno")
        }

        notebookRepository.deleteById(id)
    }

    private fun getNotebookById(id: UUID): Notebook =
        notebookRepository.findById(id).orElseThrow { throw ResourceNotFoundException("Caderno não encontrado") }

    private fun getUser(): User {
        val email = SecurityContextHolder.getContext().authentication?.name ?: ""
        return userRepository.findByEmail(email) ?: throw IllegalArgumentException("Usuário não encontrado")
    }
}