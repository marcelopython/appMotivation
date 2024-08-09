package com.example.motivation.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infrastructure.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.model.Phrase

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Handle
        this.handleUsername()
        this.handleStartPhrase()
        this.handlePhrase()

        // Eventos
        binding.btnNextText.setOnClickListener(this)
        binding.imgInfinito.setOnClickListener(this)
        binding.imgUser.setOnClickListener(this)
        binding.imgSun.setOnClickListener(this)

    }

    private fun handleStartPhrase() {
        val category = SecurityPreferences(this).getString(MotivationConstants.KEY.CATEGORY_PHRASE)
        if(category == ""){
            SecurityPreferences(this)
                .storeString(
                    MotivationConstants.KEY.CATEGORY_PHRASE,
                    MotivationConstants.CATEGORY_PHRASE.MOTIVATION
                )
        }
    }

    private fun handleUsername() {
        val name: String = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    private fun handlePhrase() {
        binding.textPhrase.text = Phrase().getPhrase(this)
    }

    override fun onClick(v: View) {

        if(v.id == R.id.btn_next_text) {
            this.handlePhrase()
        }else if(v.id == R.id.img_infinito) {

            binding.imgInfinito.setColorFilter(Color.WHITE)
            binding.imgUser.setColorFilter(Color.BLACK)
            binding.imgSun.setColorFilter(Color.BLACK)

            SecurityPreferences(this).storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                MotivationConstants.CATEGORY_PHRASE.MOTIVATION)

            this.handlePhrase()

        } else if(v.id == R.id.img_user) {

            binding.imgUser.setColorFilter(Color.WHITE)
            binding.imgInfinito.setColorFilter(Color.BLACK)
            binding.imgSun.setColorFilter(Color.BLACK)

            SecurityPreferences(this).storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                MotivationConstants.CATEGORY_PHRASE.HAPPY)

            this.handlePhrase()

        } else if(v.id == R.id.img_sun) {

            binding.imgSun.setColorFilter(Color.WHITE)
            binding.imgUser.setColorFilter(Color.BLACK)
            binding.imgInfinito.setColorFilter(Color.BLACK)

            SecurityPreferences(this)
                .storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                    MotivationConstants.CATEGORY_PHRASE.DAY)

            this.handlePhrase()
        }

    }
}