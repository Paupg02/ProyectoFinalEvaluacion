package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.RecylerRopaBinding
import kotlinx.coroutines.launch

class RecyclerAdapter(private val clickListener: (ProductosDataClass) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    private val productosList =ArrayList<ProductosDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,R.layout.recyler_ropa, parent, false)
        return ViewHolder(binding as RecylerRopaBinding)
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

}