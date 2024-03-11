package com.example.kiding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kiding.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    val api = RetroInterface.create()

    // 금지어 목록
    private val prohibitedWords = listOf("시발", "병신", "존나", "바보", "멍청이", "윤석열", "문재인", "박근혜", "이명박",
    "마약", "개", "Fuck")

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
                }
                // 특수문자 확인
                else if (containsSpecialCharacter(nickname)) {
                    binding.eclipse.visibility = View.VISIBLE
                    binding.inputError.text = "특수문자는 포함할 수 없습니다"
                }
                // 금지어 확인
                else if (containsProhibitedWord(nickname)) {
                    binding.eclipse.visibility = View.VISIBLE
                    binding.inputError.text = "금지어가 포함되어 있습니다"
                }
                // 가입 성공
                else {
                    val newUser = SignupModel(binding.inputNickname.text.toString())
                    api.signup(newUser).enqueue(object: retrofit2.Callback<SignupResult>{
                        override fun onResponse(call: Call<SignupResult>, response: Response<SignupResult>) {
                            val result = response.body()?.isSuccess ?: return
                            if(result) {
                                Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, LoginSplashActivity::class.java)
                                intent.putExtra("nickname", binding.inputNickname.text.toString())
                                startActivity(intent)
                            }
                            else
                                Toast.makeText(applicationContext, "회원가입 실패, 이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<SignupResult>, t: Throwable) {
                            Log.d("test", t.message.toString())
                        }
                    })
                }
            }
        }
    }

    private fun containsProhibitedWord(s: String): Boolean {
        val lowerCaseInput = s.toLowerCase()
        for (prohibitedWord in prohibitedWords) {
            if (lowerCaseInput.contains(prohibitedWord.toLowerCase())) {
                return true
            }
        }
        return false
    }

    private fun containsSpecialCharacter(s: String): Boolean {
        val regex = Regex("[^A-Za-z0-9가-힣]")
        return regex.containsMatchIn(s)
    }
}