package com.example.proyectofinalevaluacin_paulaplazaguirado.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Pedidos
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R

class RecyclerPedido(private val lista :ArrayList<Pedidos>):RecyclerView.Adapter<PedidosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosHolder {
        val inflater =LayoutInflater.from(parent.context)
        val v= inflater.inflate(R.layout.recycler_pedidos, parent,false)
        return PedidosHolder(v)
    }

    override fun onBindViewHolder(holder: PedidosHolder, position: Int) {
        val pedido = lista[position]
        holder.render(pedido)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface ClickListener {
        fun onItemClick(productos: ProductosDataClass)
    }
}