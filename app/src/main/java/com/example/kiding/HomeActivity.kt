package com.example.kiding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kiding.databinding.ActivityHomeBinding
import com.example.kiding.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var homeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment, homeFragment)
            .addToBackStack(null)
            .commit()
    }
}