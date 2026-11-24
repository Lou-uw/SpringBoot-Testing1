package com.example.testing.model

data class Cupon(
    val codigo: String,
    val porcentajeDescuento: Int,
    val subtotalMinimo: Double = 0.0,
) {
    // BLACK_FRIDAY solo es válido si el subtotal SUPERA 500.0 (estrictamente mayor)
    fun esAplicable(subtotal: Double): Boolean = subtotal > subtotalMinimo
}
