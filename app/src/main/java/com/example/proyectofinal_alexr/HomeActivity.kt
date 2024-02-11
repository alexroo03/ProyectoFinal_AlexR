package com.example.proyectofinal_alexr

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityHomeBinding
import java.io.IOException

class HomeActivity: ActivityWithMenu() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phitecnicos.setOnClickListener {
            // URL del video de YouTube cuyo audio quieres reproducir
            val youtubeUrl = "https://youtu.be/jm140EP3B7w?si=tNV_6GQfWeaFdDUe"

        }

        binding.biVisitas.setOnClickListener{
            startActivity(Intent(this, InsertarVActivity::class.java))
        }

    }


}
