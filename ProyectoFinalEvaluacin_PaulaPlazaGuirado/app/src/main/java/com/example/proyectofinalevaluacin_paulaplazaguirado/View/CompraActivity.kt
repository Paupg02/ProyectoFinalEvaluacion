package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityCompraBinding

class CompraActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}