package com.example.proyectofinal_alexr

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityEliminarpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore

class EliminarPActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityEliminarpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()

        binding.bEliminar.setOnClickListener {

            db.collection("Personal")
                .document(binding.ptnombre.text.toString())
                .delete()

            Toast.makeText(this, "Persona eliminada exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}