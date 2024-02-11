package com.example.proyectofinal_alexr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityInsertarvBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class InsertarVActivity: AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityInsertarvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()

        binding.insertar.setOnClickListener {
            if(binding.fecha.text.isNotEmpty() &&
                binding.nombreempresa.text.isNotEmpty() &&
                binding.idv.text.isNotEmpty() &&
                binding.trabajadoresexternos.text.isNotEmpty()){
                //INSETAR TIPOVISITA EN FIREBASE COMO UIN SPINNER
                val tipVisita = binding.stvisita.selectedItem.toString()
                //INSETAR TIPOVISITA EN FIREBASE COMO UIN SPINNER
                val trab = binding.strabajadores.selectedItem.toString()

                db.collection("Visitas").document(binding.idv.text.toString())
                    .set(mapOf(
                        "Fecha" to binding.fecha.text.toString(),
                        "Nombreempresa" to binding.nombreempresa.text.toString(),
                        "Observaciones" to binding.observaciones.text.toString(),
                        "Tipovisita" to tipVisita,
                        "Trabajadoresexternos" to binding.trabajadoresexternos.text.toString(),
                        "Trabajadorempresa" to trab
                    )
                    )

                Toast.makeText(this, "Se ha insertado la Visita", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, VisitasActivity::class.java))


            }else{
                Toast.makeText(this, "ERROR: Algún campo esta vacío", Toast.LENGTH_LONG).show()
            }
        }

        // SPINNER TIPO DE VISITA
        val tipos: Spinner = findViewById(R.id.stvisita)
        val lista = listOf("Visita Ante-Proyecto", "Visita de Inspección Técnica ", "Visita de Medidas", "Visita Seguimineto de Obra", "Visita Final de Obra")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        tipos.adapter = adaptador

        tipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                vista: View?,
                posicion: Int,
                id: Long
            ) {
                lista[posicion]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // OBTENER LOS NOMBRES Y APELLIDOS DE LOS TRABAJADORES DE FIREBASE
        db.collection("Personal")
            .get()
            .addOnSuccessListener { documents ->
                val nombresApellidos = documents.mapNotNull { it.getString("nombre") + " " + it.getString("apellido") }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresApellidos)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.strabajadores.adapter = adapter
            }
    }
}