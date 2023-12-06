package com.example.kiding

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kiding.databinding.ActivityChooseCharacterBinding
import com.example.kiding.databinding.ActivityLoginBinding

class ChooseCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseCharacterBinding

    private var is1Selected = false
    private var is2Selected = false
    private var is3Selected = false
    private var is4Selected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nickname = intent.getStringExtra("nickname")

        binding.nickname.text = nickname

        binding.ch1.setOnClickListener {
            is2Selected = false
            is3Selected = false
            is4Selected = false
            if (!is1Selected) {
                // 이미지 변경
                binding.ch1.setImageResource(R.drawable.character1_selected)
                binding.ch2.setImageResource(R.drawable.character2)
                binding.ch3.setImageResource(R.drawable.character3)
                binding.ch4.setImageResource(R.drawable.character4)
                // 텍스트 변경
                binding.text1.text = ""
                binding.text2.text = "게임을 시작해볼까요?"
                // 시작하기 버튼 보이게
                binding.startBtn.visibility = View.VISIBLE
            } else {
                // 이미지 변경
                binding.ch1.setImageResource(R.drawable.character1)
                // 텍스트 변경
                binding.text1.text = "안녕하세요 "
                binding.text2.text = "캐릭터를 선택해주세요"
                // 시작하기 버튼 안보이게
                binding.startBtn.visibility = View.INVISIBLE
            }
            is1Selected = !is1Selected
        }

        binding.ch2.setOnClickListener {
            is1Selected = false
            is3Selected = false
            is4Selected = false
            if (!is2Selected) {
                // 이미지 변경
                binding.ch1.setImageResource(R.drawable.character1)
                binding.ch2.setImageResource(R.drawable.character2_selected)
                binding.ch3.setImageResource(R.drawable.character3)
                binding.ch4.setImageResource(R.drawable.character4)
                // 텍스트 변경
                binding.text1.text = ""
                binding.text2.text = "게임을 시작해볼까요?"
                // 시작하기 버튼 보이게
                binding.startBtn.visibility = View.VISIBLE
            } else {
                // 이미지 변경
                binding.ch2.setImageResource(R.drawable.character2)
                // 텍스트 변경
                binding.text1.text = "안녕하세요 "
                binding.text2.text = "캐릭터를 선택해주세요"
                // 시작하기 버튼 안보이게
                binding.startBtn.visibility = View.INVISIBLE
            }
            is2Selected = !is2Selected
        }

        binding.ch3.setOnClickListener {
            is1Selected = false
            is2Selected = false
            is4Selected = false
            if (!is3Selected) {
                // 이미지 변경
                binding.ch1.setImageResource(R.drawable.character1)
                binding.ch2.setImageResource(R.drawable.character2)
                binding.ch3.setImageResource(R.drawable.character3_selected)
                binding.ch4.setImageResource(R.drawable.character4)
                // 텍스트 변경
                binding.text1.text = ""
                binding.text2.text = "게임을 시작해볼까요?"
                // 시작하기 버튼 보이게
                binding.startBtn.visibility = View.VISIBLE
            } else {
                // 이미지 변경
                binding.ch3.setImageResource(R.drawable.character3)
                // 텍스트 변경
                binding.text1.text = "안녕하세요 "
                binding.text2.text = "캐릭터를 선택해주세요"
                // 시작하기 버튼 안보이게
                binding.startBtn.visibility = View.INVISIBLE
            }
            is3Selected = !is3Selected
        }

        binding.ch4.setOnClickListener {
            is1Selected = false
            is2Selected = false
            is3Selected = false
            if (!is4Selected) {
                // 이미지 변경
                binding.ch1.setImageResource(R.drawable.character1)
                binding.ch2.setImageResource(R.drawable.character2)
                binding.ch3.setImageResource(R.drawable.character3)
                binding.ch4.setImageResource(R.drawable.character4_selected)
                // 텍스트 변경
                binding.text1.text = ""
                binding.text2.text = "게임을 시작해볼까요?"
                // 시작하기 버튼 보이게
                binding.startBtn.visibility = View.VISIBLE
            } else {
                // 이미지 변경
                binding.ch4.setImageResource(R.drawable.character4)
                // 텍스트 변경
                binding.text1.text = "안녕하세요 "
                binding.text2.text = "캐릭터를 선택해주세요"
                // 시작하기 버튼 안보이게
                binding.startBtn.visibility = View.INVISIBLE
            }
            is4Selected = !is4Selected
        }

        binding.startBtn.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("nickname", nickname)
            if (is1Selected) {
                intent.putExtra("character", binding.ch1.id.toString())
            } else if (is2Selected) {
                intent.putExtra("character", binding.ch2.id.toString())
            } else if (is3Selected) {
                intent.putExtra("character", binding.ch3.id.toString())
            } else {
                intent.putExtra("character", binding.ch4.id.toString())
            }
            startActivity(intent)
        }

    }
}