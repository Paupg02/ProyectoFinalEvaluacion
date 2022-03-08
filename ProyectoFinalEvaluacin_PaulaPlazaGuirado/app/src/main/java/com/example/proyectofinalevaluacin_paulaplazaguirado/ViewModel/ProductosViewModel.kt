package com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel

import androidx.lifecycle.*
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import com.example.proyectofinalevaluacin_paulaplazaguirado.View.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProductosViewModel(private val repository :ProductosRepository):ViewModel() {
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage


    fun getSavedProductos() = liveData {
        repository.productos.collect {
            emit(it)
        }
    }

}