package com.example.proyectofinal_alexr

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityEliminarpBinding
import com.google.firebase.firestore.FirebaseFirestore

class EliminarPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        // OBTENER LOS IDS DE PERSONAL DE FIREBASE
        db.collection("Personal")
            .get()
            .addOnSuccessListener { documents ->
                val id = documents.mapNotNull { it.id }
                val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, id)
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

                binding.sID.adapter = adapter
            }

        //BOTON PARA BORRAR LA PERSONA A TRAVES DE SU ID
        binding.bEliminar.setOnClickListener {
            val id = binding.sID.selectedItem.toString()

            db.collection("Personal")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Persona eliminada exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al eliminar persona: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
