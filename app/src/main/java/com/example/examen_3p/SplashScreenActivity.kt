package com.example.examen_3p

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val timer = object: CountDownTimer(2000,1000){
            override fun onTick(millisUnitilFinished: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
                startActivity(intent)

            }
        }
        timer.start()
    }
}