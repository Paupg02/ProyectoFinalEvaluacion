package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.*
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.Repository.ProductosRepository
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModel
import com.example.proyectofinalevaluacin_paulaplazaguirado.ViewModel.ProductosViewModelFactory
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityCompraBinding
import com.example.proyectofinalevaluacin_paulaplazaguirado.recycler.RecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CompraActivity : AppCompatActivity() , OnFragmentActionListener,
    RecyclerAdapter.ClickListener {
    lateinit var binding: ActivityCompraBinding
    private lateinit var productosViewModel: ProductosViewModel
    private lateinit var adapter: RecyclerAdapter
    lateinit var prefs: Prefs
    var email=""
    var id=""
    lateinit var compra : MutableList<ProductosDataClass>
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        compra = ArrayList()
        getdatos()
        val dao = ProductosDataBase.getInstance(application).productosDAO
        val repository = ProductosRepository(dao)
        val factory = ProductosViewModelFactory(repository)
        productosViewModel = ViewModelProvider(this, factory).get(ProductosViewModel::class.java)
       

        productosViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it,Toast.LENGTH_LONG).show()
            }
        })
        initRecyclerView()
        setListener()
        initDB()
    }

    private fun guardarPedido() {
        val time = System.currentTimeMillis()
        reference.child(time.toString()).setValue(compra)
            .addOnSuccessListener {
            Toast.makeText(this, "Pedido completado", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Ha ocurrido un error "+it.message, Toast.LENGTH_LONG).show()
            }
        compra.clear()
    }

    private fun initDB() {
        db= FirebaseDatabase.getInstance("https://mapasb151121-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = db.getReference("Compras")
    }

    private fun setListener() {
        binding.button.setOnClickListener {
            if (compra.size==0){
                Toast.makeText(this, getString(R.string.FinishToast), Toast.LENGTH_LONG).show()
            }else{

                guardarPedido()
            }
        }
    }

    private fun getdatos() {
        email = intent.getStringExtra("EMAIL").toString()
        prefs.guardarEmail(email)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= RecyclerAdapter({ selectedItem: ProductosDataClass -> onItemClick(selectedItem)})
        binding.recyclerView.adapter=adapter
        displayProductosList()
    }

    private fun displayProductosList() {
        productosViewModel.getSavedProductos().observe(this, Observer {
            adapter.setList(insertProductos())
            adapter.notifyDataSetChanged()
        })
    }

    fun insertProductos(): MutableList<ProductosDataClass>{
        var productos:MutableList<ProductosDataClass> = ArrayList()
        productos.add(ProductosDataClass(0, getString(R.string.Shoes), getString(R.string.ShoesDescription), R.drawable.zapatos))
        productos.add(ProductosDataClass(1, getString(R.string.Jeans), getString(R.string.JeansDescription), R.drawable.pantalon))
        productos.add(ProductosDataClass(2,getString(R.string.Tshirt),getString(R.string.TshirtDescription),R.drawable.camiseta))
        productos.add(ProductosDataClass(3,getString(R.string.Sweatshirt),getString(R.string.SweatshirtDescription),R.drawable.sudadera))
        productos.add(ProductosDataClass(4,getString(R.string.Dress),getString(R.string.DressDescription),R.drawable.vestido))
        return productos
    }

    override fun onItemClick(productos: ProductosDataClass) {
        Toast.makeText(this, getString(R.string.Add)+productos.nombre, Toast.LENGTH_LONG).show()
        compra.add(compra.size,ProductosDataClass(productos.id, productos.nombre, productos.descripcion, productos.imagen))
    }

    override fun onClickMenu(btn: Int) {
        when(btn){
            0-> {
                val i = Intent(this, MapaActivity::class.java).apply {
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