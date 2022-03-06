package com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel

import androidx.lifecycle.*
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import com.example.proyectofinalevaluacin_paulaplazaguirado.View.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProductosViewModel(private val repository :ProductosRepository):ViewModel() {
    private lateinit var productosToUpdateOrDelete: ProductosDataClass
    val inputNombre = MutableLiveData<String>()
    val inputDescripcion = MutableLiveData<String>()
    val inputImagen = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    private fun insertLibro(librosDataClass: ProductosDataClass) = viewModelScope.launch {
        val newRowId = repository.insert(librosDataClass)
        if (newRowId > -1) {
            statusMessage.value = Event("Producto insertado $newRowId")
        } else {
            statusMessage.value = Event("Error")
        }
    }

    fun getSavedProductos() = liveData {
        repository.productos.collect {
            emit(it)
        }
    }

    fun initUpdateAndDelete(productosDataClass: ProductosDataClass) {
        inputNombre.value = productosDataClass.nombre
        inputDescripcion.value = productosDataClass.descripcion
        inputImagen.value= productosDataClass.imagen.toString()
        productosToUpdateOrDelete = productosDataClass
    }

}