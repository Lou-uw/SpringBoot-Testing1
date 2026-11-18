package etec.backend.service

import etec.backend.exception.PrecondicionInvalidaException
import org.springframework.stereotype.Service

@Service
class CalculadoraDeDescuentosService {
    fun aplicarDescuentosSegunUsuario(userID: String, monto: Double): Double {
        if (monto <= 0) {
            throw PrecondicionInvalidaException("El monto debe ser mayor a cero")
        }
        return if (userID == "123") monto * 0.75 else monto
    }
}