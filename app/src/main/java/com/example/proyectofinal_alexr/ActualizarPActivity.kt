package com.example.proyectofinal_alexr

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityActualizarpBinding
import com.google.firebase.firestore.FirebaseFirestore

class ActualizarPActivity:AppCompatActivity() {
    private lateinit var binding: ActivityActualizarpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarpBinding.inflate(layoutInflater)
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

        //OBTENER EL LISTADO DEL SPINNER
        // Creo un objeto de la clase spinner
        val titulos: Spinner = findViewById(com.example.proyectofinal_alexr.R.id.sPuesto)
        val lista = listOf("Delineante", "Limpieza", "Programador", "Ingeniero")
        val adaptador = ArrayAdapter(this, R.layout.simple_spinner_item, lista)
        adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        titulos.adapter = adaptador

        titulos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

        //MOSTRAR LOS DATOS
        db.collection("Personal")
            .document(binding.sID.textAlignment.toString())
            .get()
            .addOnSuccessListener { document ->
                val nombre = document.getString("nombre")
                val apellido = document.getString("apellido")
                val telefono = document.getString("telefono")
                val correo = document.getString("correo")

                binding.nombre.setText(nombre ?: "")
                binding.apellido.setText(apellido ?: "")
                binding.telefono.setText(telefono ?: "")
                binding.correo.setText(correo ?: "")
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al obtener datos de la persona: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}