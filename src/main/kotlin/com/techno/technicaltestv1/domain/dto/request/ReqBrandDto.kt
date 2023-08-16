package com.techno.technicaltestv1.domain.dto.request



import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ReqBrandDto(

    @field:Size(max = 10, message = "Field must not exceed 10 characters")
    @field:Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Field must be alphanumeric")

    val descBrand: String? = null,
)
