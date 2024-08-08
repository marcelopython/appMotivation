package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infrastructure.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        binding = ActivityUserBinding.inflate(layoutInflater);
        setContentView(binding.root)

        this.viewPhrase()


        supportActionBar?.hide()

        binding.btnSave.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save){
            handleSave()
        }
    }

    private fun viewPhrase() {
        val name: String = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        if(name != ""){
            this.mainActivity()
        }
    }

    private fun mainActivity() {
        // Faz a criação e a navegação entre activitys
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //Destroi e tira da memoria essa tela apos a navegação
        finish()
    }

    private fun handleSave() {

        val name = binding.editText.text.toString()

        if(name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            this.mainActivity()
        } else{
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }

    }

}