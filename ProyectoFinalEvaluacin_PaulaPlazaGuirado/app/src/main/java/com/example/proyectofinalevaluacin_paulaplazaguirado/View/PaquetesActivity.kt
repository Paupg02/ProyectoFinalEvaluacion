package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityPaquetesBinding

class PaquetesActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaquetesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaquetesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}