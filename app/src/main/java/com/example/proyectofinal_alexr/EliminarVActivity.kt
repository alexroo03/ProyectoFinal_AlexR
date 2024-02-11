package com.example.proyectofinal_alexr

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityEliminarvBinding
import com.google.firebase.firestore.FirebaseFirestore

class EliminarVActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityEliminarvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()

        // OBTENER LOS IDS DE PERSONAL DE FIREBASE
        db.collection("Visitas")
            .get()
            .addOnSuccessListener { documents ->
                val id = documents.mapNotNull { it.id }
                val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, id)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.sID.adapter = adapter
            }

        //BOTON PARA BORRAR LA PERSONA A TRAVES DE SU ID
        binding.bEliminar.setOnClickListener {
            val id = binding.sID.selectedItem.toString()

            db.collection("Visitas")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Visita eliminada exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al eliminar la visita: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}