package com.example.kiding

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Chronometer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kiding.databinding.ActivityKikisdayRandomDiceBinding
import java.util.Random

class KikisdayRandomDiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisdayRandomDiceBinding

    private lateinit var chronometer: Chronometer

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayRandomDiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        // 이전 화면으로 전환
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            val previousActivity = when (intent.getIntExtra("currentNumber", 0)) {
                2 -> Kikisday2Activity::class.java
                3 -> Kikisday3Activity::class.java
                4 -> Kikisday4Activity::class.java
                else -> KikisdaySongActivity::class.java
            }
            intent = Intent(this, previousActivity)
            intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
            startActivity(intent)
        }

        // GestureDetector 초기화
        gestureDetector = GestureDetector(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // GestureDetector에 터치 이벤트 전달
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            // 아래에서 위로 스크롤 시
            if (distanceY > 0) {
                // 화살표 삭제
                binding.diceSwipe.visibility = View.INVISIBLE
                // 랜덤으로 dice1, dice2, dice3 중 하나를 표시
                val randomDice = when (Random().nextInt(3)) {
                    0 -> R.raw.dice1
                    1 -> R.raw.dice2
                    else -> R.raw.dice3
                }
                // gif로 전환
                Glide.with(this@KikisdayRandomDiceActivity)
                    .asGif()
                    .load(randomDice)
                    .listener(object : RequestListener<GifDrawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: GifDrawable?,
                            model: Any?,
                            target: Target<GifDrawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            // GIF Drawable이 준비되면 loop count를 설정합니다.
                            resource?.setLoopCount(1)

                            // GIF 재생이 끝나면 화면 전환
                            resource?.clearAnimationCallbacks() // 이전 콜백 제거
                            resource?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                                override fun onAnimationEnd(drawable: Drawable?) {
                                    super.onAnimationEnd(drawable)
                                    val randomNumber = when (randomDice) {
                                        R.raw.dice1 -> 1
                                        R.raw.dice2 -> 2
                                        else -> 3
                                    }
                                    val totalDice = intent.getIntExtra("currentNumber", 0) + randomNumber
                                    Log.d("currentNumber", intent.getIntExtra("currentNumber", 0).toString())
                                    Log.d("randomNumber", randomNumber.toString())
                                    Log.d("totalDice", totalDice.toString())
                                    val nextActivity = when (totalDice) {
                                        2 -> Kikisday2Activity::class.java
                                        3 -> Kikisday3Activity::class.java
                                        4 -> Kikisday4Activity::class.java
                                        5 -> Kikisday5Activity::class.java
                                        6 -> Kikisday6Activity::class.java
                                        else -> Kikisday7Activity::class.java
                                    }
                                    val intent = Intent(this@KikisdayRandomDiceActivity, nextActivity)
                                    intent.putExtra("elapsedTime", SystemClock.elapsedRealtime() - chronometer.base)
                                    Log.d("randomNumber", randomNumber.toString())
//                                    when (randomNumber) {
//                                        1 -> intent.putExtra("dice", 1)
//                                        2 -> intent.putExtra("dice", 2)
//                                        else -> intent.putExtra("dice", 3)
//                                    }
                                    intent.putExtra("dice", randomNumber)
                                    startActivity(intent)
                                }
                            })

                            return false
                        }
                    })
                    .into(binding.dice)
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }
}