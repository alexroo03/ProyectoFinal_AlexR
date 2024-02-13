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

        //BOTON PARA MOSTRAR EL MENU DE INSERTAR PERSONAL
        binding.insertar.setOnClickListener{
            startActivity(Intent(this, InsertarVActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE ELIMINAR PERSONAL
        binding.eliminar.setOnClickListener{
            startActivity(Intent(this, EliminarVActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE ELIMINAR PERSONAL
        binding.actualizar.setOnClickListener{
            startActivity(Intent(this, ActualizarVActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE MOSTRAR PERSONAL
        binding.mostrar.setOnClickListener{
            startActivity(Intent(this, MostrarVActivity::class.java))
        }

        //BOTON PARA VOLVER AL MENU
        binding.menu.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE PERSONAL
        binding.personal.setOnClickListener{
            startActivity(Intent(this, PersonalActivity::class.java))
        }


    }


}