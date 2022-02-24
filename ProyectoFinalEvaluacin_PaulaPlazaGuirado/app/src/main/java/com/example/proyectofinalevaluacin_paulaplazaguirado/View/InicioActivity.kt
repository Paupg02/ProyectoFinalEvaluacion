package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityInicioBinding
import com.google.firebase.auth.FirebaseAuth

class InicioActivity : AppCompatActivity() , OnFragmentActionListener{

    lateinit var binding: ActivityInicioBinding
    lateinit var prefs: Prefs
    var email=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        getdatos()
    }

    private fun getdatos() {
        email= intent.getStringExtra("EMAIL").toString()
        prefs.guardarEmail(email)
    }

    override fun onClickMenu(btn: Int) {
        when(btn){
            0-> startActivity(Intent(this, MapaActivity::class.java))
            1-> startActivity(Intent(this, CompraActivity::class.java))
            2-> startActivity(Intent(this, PaquetesActivity::class.java))
            3->{
                prefs.borrarTodo()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}