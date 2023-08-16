package com.techno.technicaltestv1.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResBaseDto<T>(

    val OUT_STAT : Boolean = true,


    val OUT_MESS : String = "Success",


    val OUT_DATA : T? = null,

    val code: Int = 200,
    val error: T? = null
)
