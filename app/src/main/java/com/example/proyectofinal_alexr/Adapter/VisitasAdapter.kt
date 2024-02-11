package com.example.proyectofinal_alexr.Adapter

import VisitasViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.R
import com.example.proyectofinal_alexr.Visitas

class VisitasAdapter(private var visitasList:List<Visitas>): RecyclerView.Adapter<VisitasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VisitasViewHolder(layoutInflater.inflate(R.layout.item_visitas,parent, false))
    }

    override fun onBindViewHolder(holder: VisitasViewHolder, position: Int) {
        val item = visitasList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return visitasList.size
    }

    fun actualizarVisitas(visitasLista: List<Visitas>) {
        this.visitasList = visitasLista
        notifyDataSetChanged()
    }
}