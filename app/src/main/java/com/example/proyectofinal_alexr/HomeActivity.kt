package com.example.proyectofinal_alexr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityHomeBinding

class HomeActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phitecnicos.setOnClickListener {
            // URL que deseas abrir en el navegador
            val url = "https://phitecnicos.com/"

            // Crea un Intent con la acción ACTION_VIEW y la URL como datos
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)

            // Verifica si hay alguna aplicación que pueda manejar esta acción
            if (intent.resolveActivity(packageManager) != null) {
                // Si hay una aplicación disponible, inicia la actividad
                startActivity(intent)
            } else {
                 Toast.makeText(this, "No se puede abrir la URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}