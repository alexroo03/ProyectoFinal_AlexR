package com.example.proyectofinal_alexr.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.databinding.ItemPersonalBinding

class PersonalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonalBinding.bind(view)

    fun render(personalModel: Personal) {
        binding.tvnombre.text = personalModel.nombre
        binding.tvapellido.text = personalModel.apellido
        binding.tvpuesto.text = personalModel.puesto
        binding.tvtelefono.text = personalModel.telefono
        binding.tvemail.text = personalModel.correo
        binding.tvid.text = personalModel.id
    }


}
