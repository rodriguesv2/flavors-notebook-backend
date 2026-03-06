package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookResponse
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookUpdateRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/notebooks")
class NotebookController(
    private val notebookService: NotebookService
) {

    @PostMapping
    fun create(
        @Valid @RequestBody request: NotebookCreateRequest
    ): ResponseEntity<NotebookResponse> {
        val response = notebookService.createNotebook(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping
    fun list(): ResponseEntity<List<NotebookResponse>> {
        val notebooks = notebookService.listNotebooks()
        return ResponseEntity.ok(notebooks)
    }

    @GetMapping("/{id}")
    fun getNotebook(
        @PathVariable id: UUID
    ): ResponseEntity<NotebookResponse> {
        val notebook = notebookService.getNotebook(id)
        return ResponseEntity.ok(notebook)
    }

    @PutMapping("/{id}")
    fun updateNotebook(
        @PathVariable id: UUID,
        @Valid @RequestBody request: NotebookUpdateRequest
    ): ResponseEntity<NotebookResponse> {
        val notebook = notebookService.updateNotebook(
            id = id,
            request = request
        )
        return ResponseEntity.ok(notebook)
    }

    @DeleteMapping("/{id}")
    fun deleteNotebook(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        notebookService.deleteNotebook(id)
        return ResponseEntity.noContent().build()
    }

}