package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Pedidos
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.ProductosDataClass
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityPaquetesBinding
import com.example.proyectofinalevaluacin_paulaplazaguirado.recycler.RecyclerPedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PaquetesActivity : AppCompatActivity() , OnFragmentActionListener {
    lateinit var binding: ActivityPaquetesBinding
    lateinit var prefs: Prefs
    var email=""
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaquetesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        getdatos()
        initDB()
        //Da error al bajar las cosas de firebase
        //pintarBD()
    }

    private fun pintarBD() {
        val postListener =  object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista : ArrayList<Pedidos> = ArrayList()
                for (data in snapshot.children){
                        val datosPedido = data.getValue<Pedidos>(Pedidos::class.java)
                        if (datosPedido != null) {
                            lista.add(datosPedido)
                        } else continue
                        lista.sortBy { pedido -> pedido.idPedido }
                        setRecyle(lista)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error:>>>>>>", error.message.toString())
            }
        }
        reference.addValueEventListener(postListener)
    }

    private fun setRecyle(lista: ArrayList<Pedidos>) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewPaquetes.layoutManager = linearLayoutManager
        binding.recyclerViewPaquetes.adapter = RecyclerPedido(lista)
        binding.recyclerViewPaquetes.scrollToPosition(lista.size-1)
    }

    private fun initDB() {
        db= FirebaseDatabase.getInstance("https://mapasb151121-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = db.getReference("Compras")
    }

    private fun getdatos() {
        email= intent.getStringExtra("EMAIL").toString()
        prefs.guardarEmail(email)
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
                val i = Intent(this, CompraActivity::class.java).apply {
                    putExtra("EMAIL", email)
                }
                startActivity(i)
            }
            2-> {
                Toast.makeText(this,getString(R.string.fragmentThere), Toast.LENGTH_LONG).show()
            }
            3->{
                prefs.borrarTodo()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}