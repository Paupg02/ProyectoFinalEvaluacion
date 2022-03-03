package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataBase
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModel
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModelFactory
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityCompraBinding
import com.example.proyectofinalevaluacin_paulaplazaguirado.recycler.RecyclerAdapter

class CompraActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompraBinding
    private lateinit var productosViewModel: ProductosViewModel
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = ProductosDataBase.getInstance(application).productosDAO
        val repository = ProductosRepository(dao)
        val factory = ProductosViewModelFactory(repository)

        initRecyclerView()
    }

    private fun initRecyclerView() {

    }


    fun insertProductos(): MutableList<ProductosDataClass>{
        var productos:MutableList<ProductosDataClass> = ArrayList()
        productos.add(ProductosDataClass(0, "Zapato", "Un par de zapatos con plataformas", R.drawable.zapatos))
        productos.add(ProductosDataClass(1, "Pantalon", "Un pantalon roto", R.drawable.pantalon))
        return productos
    }
}