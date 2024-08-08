package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infrastructure.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import kotlin.random.Random

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

        val category = SecurityPreferences(this).getString(MotivationConstants.KEY.CATEGORY_PHRASE)
        if(category == ""){
            SecurityPreferences(this)
                .storeString(
                    MotivationConstants.KEY.CATEGORY_PHRASE,
                    MotivationConstants.CATEGORY_PHRASE.MOTIVATION
                )
        }

        this.handlePhrase()

        // Eventos
        binding.btnNextText.setOnClickListener(this)
        binding.imgInfinito.setOnClickListener(this)
        binding.imgUser.setOnClickListener(this)
        binding.imgSun.setOnClickListener(this)

    }

    private fun handleUsername() {
        val name: String = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    private fun handlePhrase() {
        binding.textPhrase.text = this.getPhrase()
    }

    private fun getPhrase(): String {

        val phrases = phrases()

        val indexPhase: Int = Random.nextInt(phrases?.count() ?: 1 )

        val text: String = phrases?.get(indexPhase) ?: ""

        if(text == ""){
            Toast.makeText(this, "Categoria invalida!", Toast.LENGTH_SHORT).show()
        }

        return text
    }

    private fun phrases(): Array<String>? {

        val allPhrases: MutableMap<String, Array<String>> = mutableMapOf()

        allPhrases[MotivationConstants.CATEGORY_PHRASE.MOTIVATION] = arrayOf(
            "O sucesso é a soma de pequenos esforços repetidos dia após dia.",
            "Acredite que você pode, assim você já está no meio do caminho.",
            "A única maneira de fazer um excelente trabalho é amar o que você faz.",
            "Não importa o quão devagar você vá, desde que você não pare.",
            "A persistência é o caminho do êxito."
        )

        allPhrases[MotivationConstants.CATEGORY_PHRASE.HAPPY] = arrayOf(
            "A felicidade não é algo pronto. Ela vem das suas próprias ações.",
            "A verdadeira felicidade está na própria casa, entre as alegrias da família.",
            "A felicidade é quando o que você pensa, o que você diz e o que você faz estão em harmonia.",
            "A felicidade é a única coisa que podemos dar sem possuir.",
            "A felicidade não depende do que você tem ou quem você é. Depende apenas do que você pensa."
        )

        allPhrases[MotivationConstants.CATEGORY_PHRASE.DAY] = arrayOf(
            "Bom dia! Que seu dia seja repleto de sorrisos, alegrias e conquistas.",
            "Que seu dia comece bem e termine ainda melhor. Bom dia!",
            "Cada manhã traz novas oportunidades. Aproveite-as ao máximo! Bom dia!",
            "Bom dia! Que hoje você encontre a beleza nas pequenas coisas e a força para superar qualquer desafio.",
            "Que seu dia seja tão brilhante quanto o sol da manhã. Bom dia!"
        )

        return allPhrases[SecurityPreferences(this).getString(MotivationConstants.KEY.CATEGORY_PHRASE)]
    }

    override fun onClick(v: View) {

        if(v.id == R.id.btn_next_text) {
            this.handlePhrase()
        }else if(v.id == R.id.img_infinito) {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                MotivationConstants.CATEGORY_PHRASE.MOTIVATION)
            this.handlePhrase()

        } else if(v.id == R.id.img_user) {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                MotivationConstants.CATEGORY_PHRASE.HAPPY)
            this.handlePhrase()

        } else if(v.id == R.id.img_sun) {

            SecurityPreferences(this)
                .storeString(MotivationConstants.KEY.CATEGORY_PHRASE,
                    MotivationConstants.CATEGORY_PHRASE.DAY)


            this.handlePhrase()
        }

    }
}