package com.example.proyectofinal_alexr.Adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.InsertarPActivity
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

        binding.IBeliminar.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val id = binding.tvemail.text.toString()

            db.collection("Personal")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(itemView.context, "Personal eliminado correctamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(itemView.context, "Error al eliminar personal: $e", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error al eliminar personal", e)
                }
        }
    }

    companion object {
        private const val TAG = "PersonalViewHolder"
    }


}
