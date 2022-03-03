package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.RecylerRopaBinding
import com.squareup.picasso.Picasso

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val nombre = view.findViewById(R.id.txt_nombre) as TextView
    val descripcion = view.findViewById(R.id.txt_descripcion) as TextView
    fun bind(producto:ProductosDataClass, context: Context){
        nombre.text = producto.nombre
        descripcion.text = producto.descripcion
    }

}