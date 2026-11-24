package etec.backend.service

import etec.backend.exception.PrecondicionInvalidaException
import etec.backend.repository.CuponRepository
import org.springframework.stereotype.Service

@Service
class ProcesadorDeComprasService(
    private val cuponRepository: CuponRepository,
) {
    fun calcularMontoFinal(subtotal: Double, codigoCupon: String): Double {
        if (subtotal <= 0) {
            throw PrecondicionInvalidaException("El monto total de la compra debe ser mayor a cero")
        }

        val descuento = cuponRepository.descuentoDe(codigoCupon)
            ?: throw PrecondicionInvalidaException("El cupón proporcionado no existe")

        val aplicado = if (codigoCupon == "BLACK_FRIDAY" && subtotal <= 500.0) 0 else descuento
        return subtotal * (100 - aplicado) / 100
    }
}