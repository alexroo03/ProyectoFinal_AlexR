package com.example.proyectofinal_alexr.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.R

class PersonalAdapter(private var personalList:List<Personal>): RecyclerView.Adapter<PersonalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonalViewHolder(layoutInflater.inflate(R.layout.item_personal,parent,false))
    }

    override fun onBindViewHolder(holder: PersonalViewHolder, position: Int) {
        val item = personalList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return personalList.size
    }

    fun actualizarPersonal(personalLista: List<Personal>) {
        this.personalList = personalLista
        notifyDataSetChanged()
    }
}