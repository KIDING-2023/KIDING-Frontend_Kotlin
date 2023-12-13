package com.example.kiding

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Chronometer
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.kiding.databinding.ActivityKikisdayDiceBinding
import javax.sql.DataSource

class KikisdayDiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKikisdayDiceBinding

    private lateinit var chronometer: Chronometer

    private lateinit var gestureDetector: GestureDetector

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKikisdayDiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chronometer = binding.chronometer
        val elapsedTime = intent.getLongExtra("elapsedTime", 0)
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()

        // 튜토리얼 2 화면으로 전환
        binding.backBtn.setOnClickListener {
            chronometer.stop()
            intent = Intent(this, KikisdayTutorial2Activity::class.java)
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
            // 아래에서 위로 스크롤 시 gif 표시
            if (distanceY > 0) {
                Glide.with(this@KikisdayDiceActivity)
                    .asGif()
                    .load(R.raw.dice1) // your_gif_file_name에는 실제 GIF 파일의 이름이 들어가야 합니다.
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
                            target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            // GIF Drawable이 준비되면 loop count를 설정합니다.
                            resource?.setLoopCount(1)
                            return false
                        }
                    })
                    .into(binding.dice)
//                val intent = Intent(this@KikisdayDiceActivity, KikisdaySongActivity::class.java)
//                startActivity(intent)
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }

}