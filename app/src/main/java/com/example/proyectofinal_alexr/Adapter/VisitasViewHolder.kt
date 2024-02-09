package com.example.proyectofinal_alexr.Adapter

import com.example.proyectofinal_alexr.Personal
import com.google.firebase.firestore.FirebaseFirestore
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Visitas
import com.example.proyectofinal_alexr.databinding.ItemVisitasBinding

class VisitasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemVisitasBinding.bind(view)
    fun render(visitasModel: Visitas) {
        binding.tvfecha.text = visitasModel.fecha
        binding.tvnombreempresa.text = visitasModel.nombreEmpresa
        binding.tvobservaciones.text = visitasModel.Observaciones
        binding.tvtipovisita.text = visitasModel.TipoVisitas
        binding.tvtrabajadoresexternos.text = visitasModel.TrabajadoresExternos
        binding.tvtrabajadorempresa.text = visitasModel.TrabajadorEmpresa


    }
}