package com.techno.technicaltestv1.domain.entity

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "brand")
data class BrandEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name="id", columnDefinition = "bigint")
    val id: Int? = null,

    @field:Column(name="CD_BRAND", columnDefinition = "varchar(255)")
    val cdBrand: String? = null,

    @field:Column(name="DESC_BRAND", columnDefinition = "varchar(255)")
    val descBrand: String? = null,
)
