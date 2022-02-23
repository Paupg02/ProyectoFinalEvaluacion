package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}