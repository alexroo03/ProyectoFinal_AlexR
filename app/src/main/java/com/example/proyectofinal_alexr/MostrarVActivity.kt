package com.example.proyectofinal_alexr

import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Adapter.VisitasAdapter
import com.example.proyectofinal_alexr.databinding.ActivityMostrarvBinding
import com.google.firebase.firestore.FirebaseFirestore

class MostrarVActivity : ActivityWithMenu()  {

    private lateinit var listaVisitas: ArrayList<Visitas>
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: VisitasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMostrarvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Añadimos una linea de separacion entre visita y visita
        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recycler.addItemDecoration(decoration)

        // Creamos una editText para hacer un buscador para filtrar por nombre
        binding.buscador.addTextChangedListener { buscar ->
            val filtroVisita = VisitasProvider.vLista.filter { visitas ->
                visitas.Nombreempresa.lowercase().contains(buscar.toString().lowercase())
            }
            adapter.actualizarVisitas(filtroVisita)
        }

        // Inicializa la lista de Personal y el RecyclerView
        listaVisitas = ArrayList()
        recycler = binding.recycler
        recycler.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador
        adapter = VisitasAdapter(listaVisitas)
        recycler.adapter = adapter

        // Llamada al método para cargar los datos de personal
        cargarDatos()
    }

    private fun cargarDatos() {
        // Obtiene una instancia de la base de datos Firestore y el email del usuario actual
        val db = FirebaseFirestore.getInstance()

        db.collection("Visitas")
            .get()
            .addOnSuccessListener { cargar ->
                cargar.forEach { document ->
                    val visit = document.toObject(Visitas::class.java)
                    listaVisitas.add(visit)
                }
                // Notifica al adaptador de los cambios en los datos después de cargarlos
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Cargar", "Error en la obtención de visitas", exception)
            }

    }
}