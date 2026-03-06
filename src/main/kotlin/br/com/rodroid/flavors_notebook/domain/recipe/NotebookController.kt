package br.com.rodroid.flavors_notebook.domain.recipe

import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookCreateRequest
import br.com.rodroid.flavors_notebook.domain.recipe.dto.NotebookResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}