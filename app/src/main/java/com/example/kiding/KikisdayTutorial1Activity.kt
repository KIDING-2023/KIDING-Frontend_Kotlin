package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Chronometer
import com.example.kiding.databinding.ActivityKikisdayTutorial1Binding

class KikisdayTutorial1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisdayTutorial1Binding

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayTutorial1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        // 플레이 화면으로 전환
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, KikisdayPlayActivity::class.java)
            startActivity(intent)
        }

        // 튜토리얼2 화면으로 전환
        binding.okBtn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, KikisdayTutorial2Activity::class.java)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            startActivity(intent)
        }
    }

}