package com.example.proyectofinal_alexr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal_alexr.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar OnClickListener para el botón de registro
        binding.registrarse.setOnClickListener{
            val db = FirebaseFirestore.getInstance()

            // Verificar si los campos están llenos
            if(binding.nombre.text.isNotEmpty() && binding.correoE.text.isNotEmpty() && binding.contrasena.text.isNotEmpty()) {
                // Crear un nuevo usuario en Firebase Authentication
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.correoE.text.toString(), binding.contrasena.text.toString()
                )
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            // Si el registro es exitoso, guardar el nombre del usuario en Firestore
                            db.collection("usuarios").document(binding.correoE.text.toString())
                                .set(mapOf(
                                    "nombre" to binding.nombre.text.toString()
                                ))
                            // Navegar a la actividad HomeActivity
                            startActivity(Intent(this, HomeActivity::class.java))
                        }
                        else {
                            // Mostrar mensaje si el registro falla
                            Toast.makeText(this,"No se ha podido registrar el usuario", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else {
                // Mostrar mensaje si algún campo está vacío
                Toast.makeText(this, "Algun campo está vacío", Toast.LENGTH_LONG).show()
            }
        }
    }
}
