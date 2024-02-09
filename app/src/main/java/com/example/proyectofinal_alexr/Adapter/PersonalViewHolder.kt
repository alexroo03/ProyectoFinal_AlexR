package com.example.proyectofinal_alexr.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.databinding.ItemPersonalBinding
import com.google.firebase.firestore.FirebaseFirestore

class PersonalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonalBinding.bind(view)

    fun render(personalModel: Personal) {
        binding.tvnombre.text = personalModel.nombre
        binding.tvapellido.text = personalModel.apellido
        binding.tvpuesto.text = personalModel.puesto
        binding.tvtelefono.text = personalModel.telefono
        binding.tvemail.text = personalModel.correo
        binding.tvid.text = personalModel.id

        val db = FirebaseFirestore.getInstance()

        binding.IBeliminar.setOnLongClickListener {
            db.collection("Personal")
                .document(binding.tvid.text.toString())
                .delete()
                true
            }

    }
}
