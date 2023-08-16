package com.techno.technicaltestv1.repository


import com.techno.technicaltestv1.domain.entity.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository: JpaRepository<BrandEntity, String> {

    fun findByDescBrand(descBrand: String): BrandEntity?
}