package com.techno.technicaltestv1.controller

import com.techno.technicaltestv1.domain.dto.request.ReqBrandDto
import com.techno.technicaltestv1.domain.dto.request.ReqCredentialsDto
import com.techno.technicaltestv1.domain.dto.request.ReqTokenDto
import com.techno.technicaltestv1.domain.dto.response.ResBaseDto
import com.techno.technicaltestv1.domain.dto.response.ResBrandDto
import com.techno.technicaltestv1.service.MainService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/v1/apiService/crud")
class MainController(
    private val mainService: MainService
) {
    @GetMapping("/brand")
    fun getAllDataBrand(): ResponseEntity<ResBaseDto<ArrayList<ResBrandDto>>> {
        val response = mainService.getAllBrand()

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/brand")
    fun getDataBrandByFilter(@Valid @RequestBody reqBrandDto: ReqBrandDto): ResponseEntity<ResBaseDto<Any>> {
        val response = mainService.getByFilterBrand(reqBrandDto)
        return ResponseEntity.ok().body(response)
    }


    @PostMapping("/login")
    fun loginCredentials(@Valid @RequestBody reqCredentialsDto: ReqCredentialsDto): ResponseEntity<ResBaseDto<Any>> {
        val response = mainService.authLogin(reqCredentialsDto)

        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/login/token")
    fun validateToken(@RequestBody reqTokenDto: ReqTokenDto): ResponseEntity<ResBaseDto<Any>>{
        val response = mainService.authValidateToken(reqTokenDto)

        return ResponseEntity.ok().body(response)
    }
}