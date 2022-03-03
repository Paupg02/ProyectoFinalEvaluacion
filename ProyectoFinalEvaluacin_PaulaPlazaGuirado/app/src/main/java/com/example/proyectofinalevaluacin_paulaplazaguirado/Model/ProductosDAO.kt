package com.example.proyectofinalevaluacin_paulaplazaguirado.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDAO {

    @Insert
    suspend fun insertProductos(productosDataClass: ProductosDataClass):Long

    @Query("SELECT * FROM productos")
    fun getAllProductos(): Flow<List<ProductosDataClass>>
}