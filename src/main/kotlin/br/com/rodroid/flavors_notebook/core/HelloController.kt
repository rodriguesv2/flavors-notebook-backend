package br.com.rodroid.flavors_notebook.core

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloController {

    @GetMapping
    fun sayHello(): String {
        val email = SecurityContextHolder.getContext().authentication?.name ?: "Nome não encontrado"

        return "Olá, $email!"
    }
}