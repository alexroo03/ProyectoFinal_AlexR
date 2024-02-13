package com.example.proyectofinal_alexr.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.R
import com.example.proyectofinal_alexr.Visitas

// Adaptador para manejar la lista de objetos Visitas
class VisitasAdapter(private var visitasList: List<Visitas>) : RecyclerView.Adapter<VisitasViewHolder>() {

    // Método llamado para crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VisitasViewHolder(layoutInflater.inflate(R.layout.item_visitas, parent, false))
    }

    // Método llamado para asociar los datos de un elemento en una posición específica con el ViewHolder
    override fun onBindViewHolder(holder: VisitasViewHolder, position: Int) {
        val item = visitasList[position]
        holder.render(item)
    }

    // Método que devuelve el número total de elementos en la lista de visitas
    override fun getItemCount(): Int {
        return visitasList.size
    }

    // Método utilizado para actualizar la lista de objetos Visitas en el adaptador
    fun actualizarVisitas(visitasLista: List<Visitas>) {
        this.visitasList = visitasLista
        notifyDataSetChanged()
    }
}
