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
    // Declara el reproductor de medios
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el reproductor de medios
        mediaPlayer = MediaPlayer()

        binding.phitecnicos.setOnClickListener {
            // URL del video de YouTube cuyo audio quieres reproducir
            val youtubeUrl = "https://youtu.be/jm140EP3B7w?si=tNV_6GQfWeaFdDUe"

            // Función para reproducir el sonido desde la URL de YouTube
            playSoundFromUrl(youtubeUrl)
        }

        binding.biVisitas.setOnClickListener{
            startActivity(Intent(this, InsertarVActivity::class.java))
        }

    }

    // Función para reproducir el sonido desde una URL de YouTube
    private fun playSoundFromUrl(url: String) {
        try {
            // Detener y restablecer el reproductor de medios si ya está reproduciendo
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                    reset()
                }
            }

            // Establecer la fuente de datos para el reproductor de medios
            mediaPlayer?.setDataSource(url)

            // Preparar el reproductor de medios
            mediaPlayer?.prepare()

            // Iniciar la reproducción
            mediaPlayer?.start()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al reproducir el sonido", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos del reproductor de medios
        mediaPlayer?.release()
    }
}
