package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import com.example.kiding.databinding.ActivityKikisdayOrangeComplete2Binding
import com.example.kiding.databinding.ActivityKikisdayPinkCompleteBinding

class KikisdayPinkCompleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisdayPinkCompleteBinding

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayPinkCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        Log.d("currentNumber", intent.getIntExtra("currentNumber", 0).toString())
        val number = intent.getIntExtra("currentNumber", 0)

        // 이전 화면으로
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            val previousActivity = when (number) {
                17 -> Kikisday17Activity::class.java
                else -> Kikisday20Activity::class.java
            }
            intent = Intent(this, previousActivity)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            startActivity(intent)
        }

        // 랜덤 주사위 화면으로 넘어가도록 (임시)
        Handler().postDelayed({
            intent = Intent(this, KikisdayRandomDice4Activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            intent.putExtra("currentNumber", number)
            startActivity(intent)
            finish()
        }, DURATION)
    }

    // 3초로 설정 (임시)
    companion object {
        private const val DURATION : Long = 3000
    }
}