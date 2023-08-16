package com.techno.technicaltestv1.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestv1.exception.CustomExceptionHandler
import com.techno.technicaltestv1.repository.TokenRepository
import com.techno.technicaltestv1.service.MainService
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class MainInterceptor(
    private val tokenRepository: TokenRepository,
    private val mainService: MainService
): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = request.getHeader("api-key")
        val token = request.getHeader("token") // Token yang dienkripsi dari header
        val auth = request.getHeader("Authorization")

        val data = tokenRepository.findIdByToken(token) // Cari data token di basis data berdasarkan token yang telah didekripsi
        val dataApiKey = tokenRepository.findIdByApiKey(apiKey) // Cari data token di basis data berdasarkan token yang telah didekripsi

        return if (dataApiKey != null && data != null && auth.startsWith("Bearer ")) {
            if(!mainService.validateToken(token)){
                throw CustomExceptionHandler("Waktu token telah habis")
            }
            true
        }else{
            val result = mapOf<String, String>("status" to "F", "message" to "you don't have permission access data Brand")
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            false
        }

    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {

    }

    fun convertObjectToJson(dto : Map<String, String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}