package com.example.proyectofinal_alexr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal_alexr.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de los OnClickListener para los botones de iniciar sesión y registro
        binding.iniciar.setOnClickListener {
            login()
        }
        binding.registrar.setOnClickListener {
            registro()
        }
    }

    // Método para manejar el evento de registro
    private fun registro() {
        // Iniciar la actividad de registro
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    // Método para manejar el evento de inicio de sesión
    private fun login(){
        // Comprobar si los campos de correo y contraseña no están vacíos
        if(binding.correo.text.isNotEmpty() && binding.contrasena.text.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.correo.text.toString(),
                binding.contrasena.text.toString()
            )
                // Escuchar el resultado de la operación de inicio de sesión
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        // Si el inicio de sesión es exitoso, navegar a la actividad HomeActivity
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Mostrar un mensaje de error si el inicio de sesión falla
                        Toast.makeText(this, "Correo o Contraseña incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            // Mostrar un mensaje si alguno de los campos está vacío
            Toast.makeText(this, "Algun campo está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}
