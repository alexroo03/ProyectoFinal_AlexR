package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityInsertarvBinding
import com.google.firebase.firestore.FirebaseFirestore

class InsertarVActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityInsertarvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()

        binding.insertar.setOnClickListener {
            if(binding.fecha.text.isNotEmpty() &&
                binding.nombreempresa.text.isNotEmpty() &&
                binding.observaciones.text.isNotEmpty() &&
                binding.tipovisita.text.isNotEmpty() &&
                binding.trabajadoresexternos.text.isNotEmpty() &&
                binding.trabajadorempresa.text.isNotEmpty()){

                db.collection("Visitas").document()
                    .set(mapOf(
                        "fecha" to binding.fecha.text.toString(),
                        "nombreempresa" to binding.nombreempresa.text.toString(),
                        "observaciones" to binding.observaciones.text.toString(),
                        "tipovisita" to binding.tipovisita.text.toString(),
                        "trabajadoresexternos" to binding.trabajadoresexternos.text.toString(),
                        "trabajadorempresa" to binding.trabajadorempresa.text.toString()
                    )
                    )

                Toast.makeText(this, "Se ha insertado la Visita", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, VisitasActivity::class.java))


            }else{
                Toast.makeText(this, "ERROR: Algún campo esta vacío", Toast.LENGTH_LONG).show()
            }
        }
    }
}