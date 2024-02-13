package com.example.proyectofinal_alexr.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Visitas
import com.example.proyectofinal_alexr.databinding.ItemVisitasBinding
import java.text.SimpleDateFormat

class VisitasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Inicialización del binding para acceder a los elementos de diseño
    private val binding = ItemVisitasBinding.bind(view)

    // Método para renderizar los datos de un objeto Personal en la vista
    fun render(visitasModel: Visitas) {
        // Asignar los valores de los atributos del objeto Visitas a los elementos de diseño
        binding.tvfecha.text = visitasModel.Fecha
        binding.tvnombreempresa.text = visitasModel.Nombreempresa
        binding.tvobservaciones.text = visitasModel.Observaciones
        binding.tvtipovisita.text = visitasModel.Tipovisita
        binding.tvtrabajadoresexternos.text = visitasModel.Trabajadoresexternos
        binding.tvtrabajadorempresa.text = visitasModel.Trabajadorempresa
        binding.tvidv.text = visitasModel.Id
    }
}
