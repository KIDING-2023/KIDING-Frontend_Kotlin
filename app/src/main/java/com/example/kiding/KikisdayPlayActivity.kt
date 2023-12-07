package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kiding.databinding.ActivityKikisdayPlayBinding
import com.example.kiding.databinding.ActivityLoginBinding

class KikisdayPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisdayPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 홈화면으로
        binding.backBtn.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 튜토리얼1로 이동
        binding.kikisdayPlaybtn.setOnClickListener {
            intent = Intent(this, KikisdayTutorial1Activity::class.java)
            startActivity(intent)
        }
    }

}