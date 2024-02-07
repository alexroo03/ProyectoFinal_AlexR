package com.example.proyectofinal_alexr.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.databinding.ItemPersonalBinding

class PersonalViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val binding = ItemPersonalBinding.bind(view)

    fun render(personalModel:Personal){
        binding.tvnombre.text= personalModel.nombre
        binding.tvapellido.text= personalModel.apellido
        binding.tvpuesto.text= personalModel.puesto
        binding.tvtelefono.text= personalModel.telefono.toString()
        binding.tvemail.text= personalModel.correo

        //Evento de hacer clic sobre la imagen de borrar para eliminar la persona
        binding.IBeliminar.setOnClickListener{

        }
    }
}