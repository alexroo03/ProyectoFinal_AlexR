package com.example.proyectofinal_alexr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityPersonalBinding

class PersonalActivity: ActivityWithMenu() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}