package com.techno.technicaltestv1.service

import com.techno.technicaltestv1.domain.dto.request.ReqBrandDto
import com.techno.technicaltestv1.domain.dto.request.ReqCredentialsDto
import com.techno.technicaltestv1.domain.dto.request.ReqTokenDto
import com.techno.technicaltestv1.domain.dto.response.ResBaseDto
import com.techno.technicaltestv1.domain.dto.response.ResBrandDto

interface MainService {

    fun getAllBrand(): ResBaseDto<ArrayList<ResBrandDto>>
    fun getByFilterBrand(reqBrandDto: ReqBrandDto): ResBaseDto<Any>

    fun authLogin(reqCredentialsDto: ReqCredentialsDto) : ResBaseDto<Any>
    fun authValidateToken(reqTokenDto: ReqTokenDto): ResBaseDto<Any>
    fun validateToken(auth: String): Boolean
}