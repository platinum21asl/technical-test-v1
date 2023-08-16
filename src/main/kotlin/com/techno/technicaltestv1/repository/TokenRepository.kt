package com.techno.technicaltestv1.repository

import com.techno.technicaltestv1.domain.entity.TokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository: JpaRepository<TokenEntity, String> {

    fun findIdByToken(token: String): TokenEntity?
    fun findIdByApiKey(apiKey: String): TokenEntity?
}