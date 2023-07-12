package com.example.presentationassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.presentationassignment.databinding.ActivityMain3Binding
class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMain3Binding.inflate(layoutInflater)
        binding.exitagain.setOnClickListener {
            System.exit(0)
        }
        binding.playagain.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)
    }
}