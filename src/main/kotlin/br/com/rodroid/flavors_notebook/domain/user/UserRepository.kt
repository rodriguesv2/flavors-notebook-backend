package br.com.rodroid.flavors_notebook.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?
    fun findAllByEmail(email: String): User?
}