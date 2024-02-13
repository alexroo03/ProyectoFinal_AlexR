package com.example.proyectofinal_alexr.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.R

// Adaptador para manejar la lista de objetos Personal
class PersonalAdapter(private var personalList: List<Personal>) : RecyclerView.Adapter<PersonalViewHolder>(){

    // Método llamado para crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonalViewHolder(layoutInflater.inflate(R.layout.item_personal, parent, false))
    }

    // Método llamado para asociar los datos de un elemento en una posición específica con el ViewHolder
    override fun onBindViewHolder(holder: PersonalViewHolder, position: Int) {
        val item = personalList[position]
        holder.render(item)
    }

    // Método que devuelve el número total de elementos en la lista personal
    override fun getItemCount(): Int {
        return personalList.size
    }

    // Método utilizado para actualizar la lista de objetos Personal en el adaptador
    fun actualizarPersonal(personalLista: List<Personal>) {
        this.personalList = personalLista
        notifyDataSetChanged() // Notificar al RecyclerView que los datos han cambiado
    }
}
