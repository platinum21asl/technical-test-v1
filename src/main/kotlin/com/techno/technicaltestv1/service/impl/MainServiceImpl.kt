package com.techno.technicaltestv1.service.impl

import com.techno.technicaltestv1.domain.dto.request.ReqBrandDto
import com.techno.technicaltestv1.domain.dto.request.ReqCredentialsDto
import com.techno.technicaltestv1.domain.dto.request.ReqTokenDto
import com.techno.technicaltestv1.domain.dto.response.ResBaseDto
import com.techno.technicaltestv1.domain.dto.response.ResBrandDto
import com.techno.technicaltestv1.domain.dto.response.ResTokenDto
import com.techno.technicaltestv1.domain.entity.TokenEntity
import com.techno.technicaltestv1.exception.CustomExceptionHandler
import com.techno.technicaltestv1.repository.BrandRepository
import com.techno.technicaltestv1.repository.CredentialsRepository
import com.techno.technicaltestv1.repository.TokenRepository
import com.techno.technicaltestv1.service.MainService
import com.techno.technicaltestv1.util.JwtGenerator
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


@Service
class MainServiceImpl(
    private val brandRepository: BrandRepository,
    private val credentialsRepository: CredentialsRepository,
    private val tokenRepository: TokenRepository,
) : MainService {
    override fun getAllBrand(): ResBaseDto<ArrayList<ResBrandDto>> {
        val data = brandRepository.findAll()

        if(data.isEmpty()){
            return ResBaseDto(false, "Data not found", null)
        }
        val response: ArrayList<ResBrandDto> = ArrayList()
        data.forEach{
            response.add(
                ResBrandDto(
                    cdBrand = it.cdBrand!!,
                    descBrand = it.descBrand!!
                )
            )
        }

        return ResBaseDto(OUT_DATA = response)
    }

    override fun getByFilterBrand(reqBrandDto: ReqBrandDto): ResBaseDto<Any> {
        if(reqBrandDto.descBrand.toString() === ""){
            val data = brandRepository.findAll()
            val response: ArrayList<ResBrandDto> = ArrayList()
            data.forEach{
                response.add(
                    ResBrandDto(
                        cdBrand = it.cdBrand!!,
                        descBrand = it.descBrand!!
                    )
                )
            }
            return ResBaseDto(OUT_DATA = response)
        }else{
            val data = brandRepository.findByDescBrand(reqBrandDto.descBrand.toString())?: return ResBaseDto(false, "Data not found", null)

            val response = ResBrandDto(
                cdBrand = data.cdBrand!!,
                descBrand = data.descBrand!!
            )

            return ResBaseDto(OUT_DATA = response)
        }
    }

    override fun authLogin(reqCredentialsDto: ReqCredentialsDto): ResBaseDto<Any> {
        val dataCredentials = credentialsRepository.findByGrantType(reqCredentialsDto.grantType.toString())
            ?: throw CustomExceptionHandler("Credentials Not Found")

        var expiredJwt = System.currentTimeMillis()+300000L
        val expiredDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(expiredJwt), ZoneId.systemDefault())
        val token = JwtGenerator().createJwt(
            reqCredentialsDto.grantType!!,
            expiredJwt
        )
        val apiKey = UUID.randomUUID().toString()

        val data =TokenEntity(
            token = token,
            tokenType ="Bearer",
            apiKey = apiKey,
            scope =  "Random Message",
            expiredIn = expiredDate,
            idCredentials = dataCredentials
        )

        val dataEntity = tokenRepository.save(data)

        val response = ResTokenDto(
            accessToken = dataEntity.token!!,
            tokenType ="Bearer",
            scope =  "Random Message",
            expiredIn = expiredDate,
        )

        return ResBaseDto(OUT_DATA = response)
    }

    override fun authValidateToken(reqTokenDto: ReqTokenDto): ResBaseDto<Any> {
        val data = tokenRepository.findIdByToken(reqTokenDto.token.toString()) ?: throw CustomExceptionHandler("Token Not Found or Expired")

        val isTokenValid = validateToken(reqTokenDto.token.toString())
        println(data.expiredIn)

        return ResBaseDto(OUT_DATA = null)
    }

    override fun validateToken(auth: String): Boolean {

        val data = tokenRepository.findIdByToken(auth) ?: throw CustomExceptionHandler("Token not valid")
        val dateTimeString = data.expiredIn.toString()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter)
        val currentTime = LocalDateTime.now()
        if (currentTime.isAfter(dateTime)) {
            throw CustomExceptionHandler("Waktu token telah habis")
        }
        return true
    }
}