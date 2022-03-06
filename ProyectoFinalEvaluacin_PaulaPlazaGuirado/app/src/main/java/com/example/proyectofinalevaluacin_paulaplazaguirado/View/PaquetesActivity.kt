package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityPaquetesBinding
import com.google.firebase.auth.FirebaseAuth

class PaquetesActivity : AppCompatActivity() , OnFragmentActionListener {
    lateinit var binding: ActivityPaquetesBinding
    lateinit var prefs: Prefs
    var email=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaquetesBinding.inflate(layoutInflater)
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