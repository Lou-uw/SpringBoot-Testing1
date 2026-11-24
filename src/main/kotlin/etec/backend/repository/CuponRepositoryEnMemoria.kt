package etec.backend.repository

import org.springframework.stereotype.Repository

@Repository
class CuponRepositoryEnMemoria : CuponRepository {

    private val descuentos = mapOf(
        "PRIMERA_COMPRA" to 15,
        "BLACK_FRIDAY" to 30,
    )

    override fun descuentoDe(codigo: String): Int? = descuentos[codigo]
}
