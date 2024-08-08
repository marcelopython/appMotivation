package com.example.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        this.handlePhrase()

        // Eventos
        binding.btnNextText.setOnClickListener(this)

    }

    private fun handleUsername() {
        val name: String = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    private fun handlePhrase() {
        binding.textPhrase.text = this.getPhrase()
    }

    private fun getPhrase(): String {
        val indexPhase: Int = Random.nextInt(20)
        return phrases()[indexPhase]
    }

    private fun phrases(): Array<String> {
        return arrayOf(
            "Acredite em você e em tudo que você é.",
            "O sucesso é a soma de pequenos esforços repetidos dia após dia.",
            "A única maneira de fazer um excelente trabalho é amar o que você faz.",
            "Nunca é tarde demais para ser aquilo que você sempre desejou ser.",
            "O fracasso é apenas a oportunidade de começar de novo com mais inteligência.",
            "Você é mais forte do que imagina.",
            "Sonhe grande e ouse falhar.",
            "A persistência é o caminho do êxito.",
            "Acredite que você pode, assim você já está no meio do caminho.",
            "As grandes conquistas começam com a decisão de tentar.",
            "O único lugar onde o sucesso vem antes do trabalho é no dicionário.",
            "O futuro pertence àqueles que acreditam na beleza de seus sonhos.",
            "Se você pode sonhar, você pode realizar.",
            "Não espere por oportunidades, crie-as.",
            "O segredo do sucesso é a constância de propósito.",
            "A vida é 10% o que acontece com você e 90% como você reage a isso.",
            "Não importa quão devagar você vá, desde que não pare.",
            "Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado.",
            "Nunca desista de um sonho por causa do tempo que levará para realizá-lo. O tempo vai passar de qualquer forma.",
            "Acredite, você tem forças para chegar onde quiser. Basta querer."
        )

    }

    override fun onClick(v: View) {

        if(v.id == R.id.btn_next_text) {
            this.handlePhrase()
        }

    }
}