package com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import java.lang.IllegalArgumentException

class ProductosViewModelFactory (
    private val repository: ProductosRepository
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            return ProductosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
    }
