package com.example.proyectofinal_alexr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.proyectofinal_alexr.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    public lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val  ScreenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ScreenSplash.setKeepOnScreenCondition {true}

        Thread.sleep(1500)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}