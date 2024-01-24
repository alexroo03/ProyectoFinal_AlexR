package com.example.proyectofinal_alexr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal_alexr.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity(){
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrarse.setOnClickListener{
            val db = FirebaseFirestore.getInstance()

            if(binding.nombre.text.isNotEmpty() && binding.correoE.text.isNotEmpty() && binding.contrasena.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.correoE.text.toString(), binding.contrasena.text.toString()
                )
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            db.collection("usuarios").document(binding.correoE.text.toString())
                                .set(mapOf(
                                    "nombre" to binding.nombre.text.toString()
                                ))

                            startActivity(Intent(this, HomeActivity::class.java))
                        }
                        else{
                            Toast.makeText(this,"No se ha  podido registrar el usuario", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else {
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_LONG).show()
            }
        }

    }
}