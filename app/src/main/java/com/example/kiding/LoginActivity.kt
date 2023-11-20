package com.example.kiding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.kiding.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinbtn.setOnClickListener {
            val nickname = binding.inputNickname.text.toString()

            // 닉네임이 비어있는지 확인
            if (TextUtils.isEmpty(nickname)) {
                binding.eclipse.visibility = View.VISIBLE
                binding.inputError.text = "닉네임을 입력해주세요"
            } else {
                // 닉네임 길이 확인
                if (nickname.length > 5) {
                    binding.eclipse.visibility = View.VISIBLE
                    binding.inputError.text = "5글자 이하로 입력해주세요"
                } else {
                    // 특수문자 확인
                    if (containsSpecialCharacter(nickname)) {
                        binding.eclipse.visibility = View.VISIBLE
                        binding.inputError.text = "특수문자는 포함할 수 없습니다"
                    } else {
                        // 가입 성공
                    }
                }
            }
        }
    }

    private fun containsSpecialCharacter(s: String): Boolean {
        val regex = Regex("[^A-Za-z0-9가-힣]")
        return regex.containsMatchIn(s)
    }
}