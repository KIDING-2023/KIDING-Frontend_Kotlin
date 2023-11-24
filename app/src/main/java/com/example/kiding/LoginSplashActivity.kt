package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoginSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_splash)

        val nickname = intent.getStringExtra("nickname")

        // 캐릭터 선택 화면으로 넘어가도록
        Handler().postDelayed({
            val intent = Intent(this, ChooseCharacterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("nickname", nickname)
            startActivity(intent)
            finish()
        },DURATION)

    }

    // 3초로 설정
    companion object {
        private const val DURATION : Long = 3000
    }
}