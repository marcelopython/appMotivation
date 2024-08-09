package com.example.motivation.model

import android.content.Context
import android.widget.Toast
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.infrastructure.SecurityPreferences
import kotlin.random.Random

class Phrase {


    public fun getPhrase(context: Context): String {

        val phrases = phrases(context)

        val indexPhase: Int = Random.nextInt(phrases?.count() ?: 1 )

        val text: String = phrases?.get(indexPhase) ?: ""

        if(text == ""){
            Toast.makeText(context, "Categoria invalida!", Toast.LENGTH_SHORT).show()
        }

        return text
    }

    private fun phrases(context: Context): Array<String>? {

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

        return allPhrases[SecurityPreferences(context).getString(MotivationConstants.KEY.CATEGORY_PHRASE)]
    }



}