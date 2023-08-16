package com.techno.technicaltestv1.exception

import com.techno.technicaltestv1.domain.dto.response.ResBaseDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

    @ExceptionHandler(Exception::class)
    fun handlerException(exception: Exception): ResponseEntity<ResBaseDto<String>> {
        println("Error General")
        exception.printStackTrace()
        val response = (ResBaseDto(false, "Something went Wrong", null, 400, exception.message))

        return ResponseEntity.badRequest().body(response)

    }

    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any> {
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to exception.message!!)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>("status" to "False", "error" to "field", "message" to errors)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(exception: AccessDeniedException): ResponseEntity<ResBaseDto<String>> {
        val response = ResBaseDto(false, "Unauthorized", null, 401, "You are not authorized to access this resource")
        return ResponseEntity.status(401).body(response)
    }
}