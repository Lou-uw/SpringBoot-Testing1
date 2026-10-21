package com.example.testing.service

import com.example.testing.model.Item

class OrdenDeCompra {
    private val items = mutableListOf<Item>()
    fun agregarItem(item: Item) {
        this.items.add(item)
    }
    fun calcularTotal(): Int = this.items.sumOf { it.precio }
}