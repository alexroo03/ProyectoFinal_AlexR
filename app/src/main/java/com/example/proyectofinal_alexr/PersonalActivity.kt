package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import com.example.proyectofinal_alexr.databinding.ActivityPersonalBinding

class PersonalActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //BOTON PARA MOSTRAR EL MENU DE INSERTAR PERSONAL
        binding.insertar.setOnClickListener{
            startActivity(Intent(this, InsertarPActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE MOSTRAR EL PERSONAL
        binding.mostrar.setOnClickListener{
            startActivity(Intent(this, MostrarPActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE ELIMINAR PERSONAL
        binding.eliminar.setOnClickListener{
            startActivity(Intent(this, EliminarPActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE ACTUALIZAR PERSONAL
        binding.actualizar.setOnClickListener{
            startActivity(Intent(this, ActualizarPActivity::class.java))
        }

        //BOTON PARA VOLVER AL MENU
        binding.menu.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        //BOTON PARA MOSTRAR EL MENU DE VISITAS
        binding.visitas.setOnClickListener{
            startActivity(Intent(this, VisitasActivity::class.java))
        }
    }
}