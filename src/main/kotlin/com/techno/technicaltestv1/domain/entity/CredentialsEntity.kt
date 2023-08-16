package com.techno.technicaltestv1.domain.entity

import javax.persistence.*


@Entity
@Table(name = "credentials")
data class CredentialsEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", columnDefinition = "bigint")
    val id: Int? = null,

    @field:Column(name = "name_Client", columnDefinition = "varchar(255)")
    val nameClient: String? = null,

    @field:Column(name = "grant_Type", columnDefinition = "varchar(255)")
    val grantType: String? = null,
)
