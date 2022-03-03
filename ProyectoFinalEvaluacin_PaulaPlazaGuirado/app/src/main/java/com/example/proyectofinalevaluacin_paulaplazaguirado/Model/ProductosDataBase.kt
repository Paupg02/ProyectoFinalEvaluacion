package com.example.proyectofinalevaluacin_paulaplazaguirado.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductosDataClass::class], version = 1)
abstract class ProductosDataBase : RoomDatabase(){
    abstract val productosDAO: ProductosDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductosDataBase? = null
        fun getInstance(context: Context): ProductosDataBase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductosDataBase::class.java,
                        "productos"
                    ).build()
                }
                return instance
            }
        }
    }
}