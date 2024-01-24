package com.example.proyectofinal_alexr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal_alexr.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iniciar.setOnClickListener {
            login()
        }
        binding.registrar.setOnClickListener {
            registro()
        }
    }

    private fun registro() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    private fun login(){
        // Comprobamos los campos de correo y contraseña pra que no esten vacios
        if(binding.correo.text.isNotEmpty()&& binding.contrasena.text.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.correo.text.toString(),
                binding.contrasena.text.toString()
            )

                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent= Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Correo o Contraseña incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }

        }else{
            Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_SHORT).show()
        }
    }


}