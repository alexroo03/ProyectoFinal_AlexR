package com.example.proyectofinal_alexr

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import com.example.proyectofinal_alexr.databinding.ActivityHomeBinding

class HomeActivity: ActivityWithMenu() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binidng para el imageButton, para que nos lleve a la pagina web marcada
        binding.phitecnicos.setOnClickListener {
            val url = "https://phitecnicos.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            startActivity(intent)
        }

        //binding para mostrar la pagina de insertar visitas
        binding.biVisitas.setOnClickListener{
            startActivity(Intent(this, InsertarVActivity::class.java))
        }

    }


}
