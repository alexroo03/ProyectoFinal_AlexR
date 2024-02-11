package com.example.proyectofinal_alexr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityActualizarvBinding

class ActualizarVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarvBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarvBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}