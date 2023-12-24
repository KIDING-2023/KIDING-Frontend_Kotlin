package com.example.kiding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kiding.databinding.FragmentSortByMainBinding
import com.example.kiding.databinding.FragmentSortByPopularityBinding

class SortByPopularityFragment : Fragment() {

    private lateinit var binding: FragmentSortByPopularityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortByPopularityBinding.inflate(inflater, container, false)

        binding.kikisday.setOnClickListener {
            activity?.let{
                val intent = Intent(context, KikisdayPlayActivity::class.java)
                startActivity(intent)
            }
        }

        binding.space.setOnClickListener {
            activity?.let{
                val intent = Intent(context, SpacePlayActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }

}