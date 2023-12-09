package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import com.example.kiding.databinding.ActivitySpaceTutorial2Binding

class SpaceTutorial2Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySpaceTutorial2Binding

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpaceTutorial2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        // 튜토리얼 1 화면으로 전환
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, SpaceTutorial1Activity::class.java)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            startActivity(intent)
        }

        // 주사위 화면으로 전환
        binding.okBtn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, SpaceDiceActivity::class.java)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            startActivity(intent)
        }
    }
}