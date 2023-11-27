package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kiding.databinding.ActivitySpacePlayBinding

class SpacePlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpacePlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpacePlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 홈화면으로
        binding.backBtn.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}