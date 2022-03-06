package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataBase
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModel
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModelFactory
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityCompraBinding
import com.example.proyectofinalevaluacin_paulaplazaguirado.recycler.RecyclerAdapter
import com.google.firebase.auth.FirebaseAuth

class CompraActivity : AppCompatActivity() , OnFragmentActionListener {
    lateinit var binding: ActivityCompraBinding
    private lateinit var productosViewModel: ProductosViewModel
    private lateinit var adapter: RecyclerAdapter
    lateinit var prefs: Prefs
    var email=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        getdatos()
        val dao = ProductosDataBase.getInstance(application).productosDAO
        val repository = ProductosRepository(dao)
        val factory = ProductosViewModelFactory(repository)
        /*productosViewModel = ViewModelProvider(this, factory).get(ProductosViewModel::class.java)
        binding.myViewModel=productosViewModel
        binding.lifecycleOwner=this

        productosViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it,Toast.LENGTH_LONG).show()
            }
        })
        initRecyclerView()*/
    }

    private fun getdatos() {
        email= intent.getStringExtra("EMAIL").toString()
        prefs.guardarEmail(email)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= RecyclerAdapter({ selectedItem: ProductosDataClass -> listItemClicked(selectedItem)})
        binding.recyclerView.adapter=adapter
        displayProductosList()
    }

    private fun listItemClicked(librosDataClass: ProductosDataClass) {
        productosViewModel.initUpdateAndDelete(librosDataClass)
    }

    private fun displayProductosList() {
        productosViewModel.getSavedProductos().observe(this, Observer {
            adapter.setList(insertProductos())
            adapter.notifyDataSetChanged()
        })
    }

    fun insertProductos(): MutableList<ProductosDataClass>{
        var productos:MutableList<ProductosDataClass> = ArrayList()
        productos.add(ProductosDataClass(0, "Zapato", "Un par de zapatos con plataformas", R.drawable.zapatos))
        productos.add(ProductosDataClass(1, "Pantalon", "Un pantalon roto", R.drawable.pantalon))
        return productos
    }

    override fun onClickMenu(btn: Int) {
        when(btn){
            0-> {
                val i =Intent(this, MapaActivity::class.java).apply {
                    putExtra("EMAIL", email)
                }
                startActivity(i)
            }
            1-> {
                Toast.makeText(this,getString(R.string.fragmentThere),Toast.LENGTH_LONG).show()
            }
            2-> {
                val i =Intent(this, PaquetesActivity::class.java).apply {
                    putExtra("EMAIL", email)
                }
                startActivity(i)
            }
            3->{
                prefs.borrarTodo()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }


}