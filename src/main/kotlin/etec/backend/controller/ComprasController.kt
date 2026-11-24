package etec.backend.controller

import etec.backend.dto.DescuentoResponse
import etec.backend.service.ProcesadorDeComprasService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/compras")
class ComprasController(
    private val procesadorService: ProcesadorDeComprasService,
) {
    @GetMapping("/calcular-descuento")
    fun calcular(
        @RequestParam("subtotal") subtotal: Double,
        @RequestParam("cupon") cupon: String,
    ): ResponseEntity<DescuentoResponse> =
        ResponseEntity.ok(DescuentoResponse(procesadorService.calcularMontoFinal(subtotal, cupon)))
}