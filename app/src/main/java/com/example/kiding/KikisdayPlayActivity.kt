package com.example.kiding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kiding.databinding.ActivityKikisdayPlayBinding
import com.example.kiding.databinding.ActivityLoginBinding

class KikisdayPlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKikisdayPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}