package etec.backend.controller

import etec.backend.dto.ErrorResponse
import etec.backend.exception.CuponInvalidoException
import etec.backend.exception.PrecondicionInvalidaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PrecondicionInvalidaException::class)
    fun manejarPrecondicionInvalida(ex: PrecondicionInvalidaException): ResponseEntity<ErrorResponse> {
        val cuerpoError = ErrorResponse(error = ex.message ?: "Parámetros inválidos")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoError)
    }

    @ExceptionHandler(CuponInvalidoException::class)
    fun manejarCuponInvalido(ex: CuponInvalidoException): ResponseEntity<ErrorResponse> {
        val cuerpoError = ErrorResponse(error = ex.message ?: "Cupón inválido")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoError)
    }
}
