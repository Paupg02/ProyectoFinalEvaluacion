package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Pedidos
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.RecyclerPedidosBinding

class PedidosHolder (v:View):RecyclerView.ViewHolder(v){
    private val binding = RecyclerPedidosBinding.bind(v)
    lateinit var context : Context

    public fun render(pedidos: Pedidos){
        binding.txtNPedido.text=pedidos.idPedido.toString()
       //Falta rellenar el recycler
    }
}
