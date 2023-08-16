package com.techno.technicaltestv1.domain.dto.response

import java.time.LocalDateTime

data class ResTokenDto(

    val accessToken: String,
    val tokenType: String,
    val expiredIn: LocalDateTime?,
    val scope: String
)
