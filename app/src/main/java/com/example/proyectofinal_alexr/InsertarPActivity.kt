package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
            if (binding.nombre.text.isNotEmpty() &&
                binding.apellido.text.isNotEmpty() &&
                binding.idp.text.isNotEmpty() &&
                binding.telefono.text.isNotEmpty() &&
                binding.correo.text.isNotEmpty()
            ) {
                val puestoSelec = binding.sPuesto.selectedItem.toString()

                db.collection("Personal").document(binding.idp.text.toString())
                    .set(
                        mapOf(
                            "nombre" to binding.nombre.text.toString(),
                            "apellido" to binding.apellido.text.toString(),
                            "telefono" to binding.telefono.text.toString(),
                            "puesto" to puestoSelec,
                            "correo" to binding.correo.text.toString()
                        )
                    )

                Toast.makeText(this, "Se ha insertado la Persona", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, PersonalActivity::class.java))
            } else {
                Toast.makeText(this, "ERROR: Algún campo está vacío", Toast.LENGTH_LONG).show()
            }
        }

        //OBTENER EL LISTADO DEL SPINNER
        // Creo un objeto de la clase spinner
        val titulos: Spinner = findViewById(R.id.sPuesto)
        val lista = listOf("Delineante", "Limpieza", "Programador", "Ingeniero")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

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
    }

}