package com.example.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleUsername()
        // Eventos
        binding.btnNextText.setOnClickListener(this)

    }

    private fun handleUsername() {
        val name: String = SecurityPreferences(this).getString("USER_NAME")
        binding.textUserName.text = "Olá, $name!"
    }

    override fun onClick(v: View) {

        if(v.id == R.id.btn_next_text) {
            println("passou por aqui")
        }

    }
}