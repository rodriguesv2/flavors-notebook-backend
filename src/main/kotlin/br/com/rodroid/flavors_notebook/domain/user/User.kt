package br.com.rodroid.flavors_notebook.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name="users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = true)
    var password: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var authProvider: AuthProvider,

    @Column(nullable = true)
    var providerId: String? = null
)