package etec.backend.controller

import etec.backend.model.PrecondicionInvalidaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PrecondicionInvalidaException::class)
    fun manejarPrecondicionInvalida(
        excepcion: PrecondicionInvalidaException,
    ): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(error = excepcion.message ?: "El monto debe ser mayor a cero"))
}
