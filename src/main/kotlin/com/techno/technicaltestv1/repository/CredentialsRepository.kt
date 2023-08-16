package com.techno.technicaltestv1.repository

import com.techno.technicaltestv1.domain.entity.CredentialsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CredentialsRepository: JpaRepository<CredentialsEntity, String> {

    fun findByGrantType(grantType: String): CredentialsEntity?
}