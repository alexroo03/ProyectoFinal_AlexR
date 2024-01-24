package com.example.proyectofinal_alexr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityHomeBinding

class HomeActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}