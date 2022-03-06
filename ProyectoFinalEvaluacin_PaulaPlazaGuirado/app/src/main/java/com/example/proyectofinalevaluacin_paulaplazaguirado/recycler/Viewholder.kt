package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.RecylerRopaBinding
import com.squareup.picasso.Picasso

class ViewHolder(val binding: RecylerRopaBinding) : RecyclerView.ViewHolder(binding.root){

    fun render(productos: ProductosDataClass, clickListener: (ProductosDataClass) -> Unit){
        binding.txtNombre.text= productos.nombre
        binding.txtDescripcion.text = productos.descripcion
        Picasso.get().load(productos.imagen).resize(100,100).centerCrop().into(binding.ivProducto)
        binding.layout.setOnClickListener {
            clickListener(productos)

        }
    }
}