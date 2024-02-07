package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityInsertarpBinding
import com.google.firebase.firestore.FirebaseFirestore

class InsertarPActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityInsertarpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()

        binding.insertar.setOnClickListener {
            if(binding.nombre.text.isNotEmpty() &&
                binding.apellido.text.isNotEmpty() &&
                binding.idp.text.isNotEmpty() &&
                binding.telefono.text.isNotEmpty() &&
                binding.puesto.text.isNotEmpty() &&
                binding.correo.text.isNotEmpty()){

                        db.collection("Personal").document(binding.idp.text.toString())
                            .set(mapOf(
                                "nombre" to binding.nombre.text.toString(),
                                "apellido" to binding.apellido.text.toString(),
                                "telefono" to binding.telefono.text.toString(),
                                "puesto" to binding.puesto.text.toString(),
                                "correo" to binding.correo.text.toString()
                            )
                            )

                        Toast.makeText(this, "Se ha insertado la Persona", Toast.LENGTH_LONG).show()

                        startActivity(Intent(this, PersonalActivity::class.java))


            }else{
                Toast.makeText(this, "ERROR: Algún campo esta vacío", Toast.LENGTH_LONG).show()
            }
        }
    }

}