package com.example.proyectofinal_alexr.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Personal
import com.example.proyectofinal_alexr.R

class PersonalAdapter(private val personalList:List<Personal>): RecyclerView.Adapter<PersonalViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonalViewHolder(layoutInflater.inflate(R.layout.item_personal,parent,false))
    }

    override fun getItemCount(): Int {
        return personalList.size
    }

    override fun onBindViewHolder(holder: PersonalViewHolder, position: Int) {
        val item = personalList[position]
        holder.render(item)
    }
}