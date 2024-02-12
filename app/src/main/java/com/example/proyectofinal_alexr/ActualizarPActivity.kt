package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityActualizarpBinding
import com.google.firebase.firestore.FirebaseFirestore

class ActualizarPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarpBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener IDs del personal y mostrarlos en el Spinner
        db.collection("Personal")
            .get()
            .addOnSuccessListener { documents ->
                val idList = documents.mapNotNull { it.id }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.sID.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al obtener los IDs del personal", Toast.LENGTH_LONG).show()
            }

        // Manejar la selección de un ID en el Spinner
        binding.sID.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedId = binding.sID.selectedItem.toString()

                // Obtener y mostrar los datos correspondientes al ID seleccionado
                db.collection("Personal")
                    .document(selectedId)
                    .get()
                    .addOnSuccessListener { document ->
                        val nombre = document.getString("nombre")
                        val apellido = document.getString("apellido")
                        val telefono = document.getString("telefono")
                        val correo = document.getString("correo")
                        val puesto = document.getString("puesto")

                        // Mostrar los datos en los campos de texto correspondientes
                        binding.nombre.setText(nombre)
                        binding.apellido.setText(apellido)
                        binding.telefono.setText(telefono)
                        binding.correo.setText(correo)
                        val puestoIndex = puesto?.let { getIndexFromArray(it) }
                        if (puestoIndex != null) {
                            binding.sPuesto.setSelection(puestoIndex)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this@ActualizarPActivity, "Error al obtener los datos: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        // Configurar el adaptador para el Spinner de Puesto
        val tipos = arrayOf("Delineante", "Limpieza", "Programador", "Ingeniero")
        val adapterTipo = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sPuesto.adapter = adapterTipo

        // Manejar el clic del botón Actualizar
        binding.bActualizar.setOnClickListener {
            val id = binding.sID.selectedItem.toString()

            // Verificar si se ha seleccionado un ID
            if (id.isNotEmpty()) {
                val nombre = binding.nombre.text.toString()
                val apellido = binding.apellido.text.toString()
                val telefono = binding.telefono.text.toString()
                val correo = binding.correo.text.toString()
                val puesto = binding.sPuesto.selectedItem.toString()

                // Actualizar los datos en Firestore
                db.collection("Personal")
                    .document(id)
                    .update(
                        mapOf(
                            "nombre" to nombre,
                            "apellido" to apellido,
                            "telefono" to telefono,
                            "correo" to correo,
                            "puesto" to puesto
                        )
                    )
                    .addOnSuccessListener {
                        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, PersonalActivity::class.java))
                        finish() // Finalizar esta actividad después de actualizar
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al actualizar los datos: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Selecciona un ID válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para obtener el índice de un elemento en un array
    private fun getIndexFromArray(value: String): Int {
        val tipos = arrayOf("Delineante", "Limpieza", "Programador", "Ingeniero")
        return tipos.indexOf(value)
    }
}
