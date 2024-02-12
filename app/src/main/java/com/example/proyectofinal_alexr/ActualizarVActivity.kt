package com.example.proyectofinal_alexr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityActualizarvBinding
import com.google.firebase.firestore.FirebaseFirestore

class ActualizarVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarvBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener IDs de visitas y mostrarlos en el Spinner
        db.collection("Visitas")
            .get()
            .addOnSuccessListener { documents ->
                val idList = documents.mapNotNull { it.id }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.sID.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al obtener los IDs de visitas", Toast.LENGTH_LONG).show()
            }

        // Manejar la selección de un ID en el Spinner
        binding.sID.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedId = binding.sID.selectedItem.toString()

                // Obtener y mostrar los datos correspondientes al ID seleccionado
                db.collection("Visitas")
                    .document(selectedId)
                    .get()
                    .addOnSuccessListener { document ->
                        val fecha = document.getString("Fecha")
                        val nombreEmpresa = document.getString("Nombreempresa")
                        val observaciones = document.getString("Observaciones")
                        val tipoVisita = document.getString("Tipovisita")
                        val trabajadoresExternos = document.getString("Trabajadoresexternos")
                        val trabajadoresEmpresa = document.getString("Trabajadorempresa")

                        // Mostrar los datos en los campos de texto correspondientes
                        binding.fecha.setText(fecha)
                        binding.nombreempresa.setText(nombreEmpresa)
                        binding.observaciones.setText(observaciones)
                        binding.trabajadoresexternos.setText(trabajadoresExternos)

                        // Seleccionar el valor del Spinner de Tipo de Visita según el valor en Firestore
                        val tipoVisitaIndex = tipoVisita?.let { getIndexFromArray(it) }
                        if (tipoVisitaIndex != null) {
                            binding.stvisita.setSelection(tipoVisitaIndex)
                        }

                        // Seleccionar el valor del Spinner de Trabajadores Empresa según el valor en Firestore
                        val trabajadoresEmpresaIndex = trabajadoresEmpresa?.let { getIndexFromArray(it) }
                        if (trabajadoresEmpresaIndex != null) {
                            binding.strabajadores.setSelection(trabajadoresEmpresaIndex)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this@ActualizarVActivity, "Error al obtener los datos: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada aquí
            }
        }

        // Configurar el adaptador para el Spinner de Tipo de Visita
        val tiposVisita = listOf("Visita Ante-Proyecto", "Visita de Inspección Técnica", "Visita de Medidas", "Visita Seguimiento de Obra", "Visita Final de Obra")
        val adapterTiposVisita = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposVisita)
        adapterTiposVisita.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.stvisita.adapter = adapterTiposVisita

        // OBTENER LOS NOMBRES Y APELLIDOS DE LOS TRABAJADORES DE FIREBASE
        db.collection("Personal")
            .get()
            .addOnSuccessListener { documents ->
                val nombresApellidos = documents.mapNotNull { it.getString("nombre") + " " + it.getString("apellido") }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresApellidos)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.strabajadores.adapter = adapter
            }

        // Manejar el clic del botón Actualizar
        binding.bActualizar.setOnClickListener {
            val id = binding.sID.selectedItem.toString()

            // Verificar si se ha seleccionado un ID
            if (id.isNotEmpty()) {
                val fecha = binding.fecha.text.toString()
                val nombreEmpresa = binding.nombreempresa.text.toString()
                val observaciones = binding.observaciones.text.toString()
                val tipoVisita = binding.stvisita.selectedItem.toString()
                val trabajadoresExternos = binding.trabajadoresexternos.text.toString()
                val trabajadoresEmpresa = binding.strabajadores.selectedItem.toString()

                // Actualizar los datos en Firestore
                db.collection("Visitas")
                    .document(id)
                    .update(
                        mapOf(
                            "Fecha" to fecha,
                            "Nombreempresa" to nombreEmpresa,
                            "Observaciones" to observaciones,
                            "Tipovisita" to tipoVisita,
                            "Trabajadoresexternos" to trabajadoresExternos,
                            "Trabajadorempresa" to trabajadoresEmpresa
                        )
                    )
                    .addOnSuccessListener {
                        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, VisitasActivity::class.java))
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
        val tipos = arrayOf("Visita Ante-Proyecto", "Visita de Inspección Técnica", "Visita de Medidas", "Visita Seguimiento de Obra", "Visita Final de Obra")
        return tipos.indexOf(value)
    }
}
