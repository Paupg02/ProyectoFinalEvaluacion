package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.RecylerRopaBinding

class RecyclerAdapter(private val clickListener: (ProductosDataClass) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    private val productosList =ArrayList<ProductosDataClass>()
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : RecylerRopaBinding = RecylerRopaBinding.inflate(LayoutInflater.from(parent.context))
        context = parent.context
        return ViewHolder(binding , context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(productosList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    fun setList(productosDataClasses: List<ProductosDataClass>) {
        productosList.clear()
        productosList.addAll(productosDataClasses)
    }

    interface ClickListener {
        fun onItemClick(productos: ProductosDataClass)
    }
}