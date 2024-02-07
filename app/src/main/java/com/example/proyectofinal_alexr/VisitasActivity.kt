package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityVisitasBinding

class VisitasActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityVisitasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.insertar.setOnClickListener{
            startActivity(Intent(this, InsertarVActivity::class.java))
        }

        binding.menu.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.personal.setOnClickListener{
            startActivity(Intent(this, PersonalActivity::class.java))
        }
    }


}