package com.example.proyectofinal_alexr.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.databinding.ItemPersonalBinding

class PersonalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Inicialización del binding para acceder a los elementos de diseño
    private val binding = ItemPersonalBinding.bind(view)

    // Método para renderizar los datos de un objeto Personal en la vista
    fun render(personalModel: Personal) {
        // Asignar los valores de los atributos del objeto Personal a los elementos de diseño
        binding.tvnombre.text = personalModel.nombre
        binding.tvapellido.text = personalModel.apellido
        binding.tvpuesto.text = personalModel.puesto
        binding.tvtelefono.text = personalModel.telefono
        binding.tvemail.text = personalModel.correo
        binding.tvid.text = personalModel.id
    }
}
