package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import com.example.kiding.databinding.ActivityKikisday10Binding
import com.example.kiding.databinding.ActivityKikisday11Binding

class Kikisday11Activity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisday11Binding

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisday11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        Log.d("dice", intent.getIntExtra("dice", 0).toString())
        val dice = 11 - intent.getIntExtra("dice", 0)

        // 이전 화면으로
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            val intent = when (dice) {
                8 -> Intent(this, KikisdayRandomDice2Activity::class.java)
                9 -> Intent(this, KikisdayRandomDice2Activity::class.java)
                else -> Intent(this, KikisdayRandomDice3Activity::class.java)
            }
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            intent.putExtra("currentNumber", dice)
            startActivity(intent)
        }

        // 키딩칩 획득 화면으로
        binding.btn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, KikisdayOrangeComplete2Activity::class.java)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            intent.putExtra("currentNumber", 11)
            startActivity(intent)
        }
    }
}