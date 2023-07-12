package com.example.presentationassignment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import  com.example.presentationassignment.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        Handler().postDelayed({
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        },5000)
        setContentView(binding.root)
    }
}