package com.techno.technicaltestv1.domain.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "token")
data class TokenEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", columnDefinition = "bigint")
    val id: Int? = null,

    @field:Column(name = "token", columnDefinition = "varchar(255)")
    val token: String? = null,

    @field:Column(name = "token_type", columnDefinition = "varchar(255)")
    val tokenType: String? = null,

    @field:Column(name = "api_key", columnDefinition = "varchar(255)")
    val apiKey: String? = null,

    @field:Column(name = "scope", columnDefinition = "varchar(255)")
    val scope: String? = null,

    @field:Column(name = "expired_in", columnDefinition = "timestamp")
    val expiredIn: LocalDateTime? = null,

    @OneToOne
    @field:JoinColumn(name = "id_credentials", referencedColumnName = "id", columnDefinition = "bigint")
    val idCredentials: CredentialsEntity? = null,
)
