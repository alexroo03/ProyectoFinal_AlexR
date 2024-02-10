package com.example.proyectofinal_alexr

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Adapter.PersonalAdapter
import com.example.proyectofinal_alexr.databinding.ActivityMostrarpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MostrarPActivity : ActivityWithMenu() {

    private lateinit var listaPersonal: ArrayList<Personal>
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: PersonalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMostrarpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Añadimos una linea de separacion entre persona y persona
        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recycler.addItemDecoration(decoration)

        // Creamos un EditText para hacer un buscador para filtrar por nombre
        binding.buscador.addTextChangedListener { text ->
            val filtroNombre = text.toString().trim().lowercase()
            val filtroPersonal = PersonalProvider.pLista.filter { personal ->
                personal.nombre.lowercase().contains(filtroNombre)
            }
            adapter.actualizarPersonal(filtroPersonal)
        }


        // Inicializa la lista de Personal y el RecyclerView
        listaPersonal = ArrayList()
        recycler = binding.recycler
        recycler.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador
        adapter = PersonalAdapter(listaPersonal)
        recycler.adapter = adapter

        // Llamada al método para cargar los datos de personal
        cargarDatos()


    }
    private fun cargarDatos() {
        // Obtiene una instancia de la base de datos Firestore y el email del usuario actual
        val db = FirebaseFirestore.getInstance()

        // Filtrar documentos por el campo "Nombre" que tenga el valor del email del personal actual
        db.collection("Personal")
            .get()
            .addOnSuccessListener { cargar ->
                cargar.forEach { document ->
                    val persona = document.toObject(Personal::class.java)
                    listaPersonal.add(persona)
                }
                // Notifica al adaptador de los cambios en los datos después de cargarlos
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Cargar", "Error en la obtención de personas", exception)
            }

    }

}
