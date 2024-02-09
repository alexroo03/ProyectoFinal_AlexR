package com.example.proyectofinal_alexr

import android.os.Bundle
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

        binding.bEliminar.setOnClickListener {

            db.collection("Visitas")
                .document(binding.ptidv.text.toString())
                .delete()

            Toast.makeText(this, "Visita eliminada exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}