package com.example.proyectofinalevaluacin_paulaplazaguirado.Repository

import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDAO
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass

class ProductosRepository(private val dao:ProductosDAO) {
    val productos=dao.getAllProductos()

    suspend fun insert (productosDataClass: ProductosDataClass):Long{
        return dao.insertProductos(productosDataClass)
    }
}