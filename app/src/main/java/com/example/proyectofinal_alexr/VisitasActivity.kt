package com.example.proyectofinal_alexr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityVisitasBinding

class VisitasActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityVisitasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}