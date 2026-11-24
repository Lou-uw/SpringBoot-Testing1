package etec.backend.repository

interface CuponRepository {
    fun descuentoDe(codigo: String): Int?
}
