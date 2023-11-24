package com.example.kiding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kiding.databinding.ActivityChooseCharacterBinding
import com.example.kiding.databinding.ActivityLoginBinding

class ChooseCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nickname.text = intent.getStringExtra("nickname")
    }
}