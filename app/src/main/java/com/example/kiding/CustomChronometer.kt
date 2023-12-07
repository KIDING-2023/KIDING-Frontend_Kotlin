package com.example.kiding

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer

class CustomChronometer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Chronometer(context, attrs, defStyleAttr) {

    init {
        init()
    }

    private fun init() {
        // 시간이 변할 때마다 호출되는 콜백 함수 설정
        setOnChronometerTickListener { chronometer ->
            // 시간을 분과 초로 분리
            val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
            val minutes = (elapsedMillis / 60000).toInt()
            val seconds = ((elapsedMillis % 60000) / 1000).toInt()

            // 형식에 맞게 텍스트 설정
            chronometer.text = String.format("%02d분 %02d초 경과", minutes, seconds)
        }
    }
}