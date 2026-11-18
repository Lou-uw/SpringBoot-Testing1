package etec.backend.controller

import etec.backend.service.CalculadoraDeDescuentos
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/descuentos")
class DescuentosEndpoint(
    private val calculadora: CalculadoraDeDescuentos,
) {
    @GetMapping
    fun calcular(
        @RequestParam("userID") userID: String,
        @RequestParam("monto") monto: Double,
    ): ResponseEntity<DescuentoResponse> =
        ResponseEntity.ok(
            DescuentoResponse(calculadora.aplicarDescuentosSegunUsuario(userID, monto)),
        )
}
