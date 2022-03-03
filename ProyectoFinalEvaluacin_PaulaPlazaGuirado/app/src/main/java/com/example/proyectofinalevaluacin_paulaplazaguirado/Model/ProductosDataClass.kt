package com.example.proyectofinalevaluacin_paulaplazaguirado.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductosDataClass(

 @PrimaryKey(autoGenerate = true)
 var id:Int,
 var nombre : String,
 var descripcion : String,
 var imagen : Int
)
