package com.example.kiding

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kiding.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var sortByMainFragment = SortByMainFragment()
    private var sortByPopularityFragment = SortByPopularityFragment()
    private var sortByRecentFragment = SortByRecentFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // default: 메인
        sortByMain()

        binding.mainArea.setOnClickListener {
            // 메인 활성화
            binding.mainEclipse.visibility = View.VISIBLE
            binding.mainText.setTextColor(Color.parseColor("#000000"))
            binding.popularityEclipse.visibility = View.INVISIBLE
            binding.popularityText.setTextColor(Color.parseColor("#75777E"))
            binding.recentEclipse.visibility = View.INVISIBLE
            binding.recentText.setTextColor(Color.parseColor("#75777E"))
            sortByMain()
        }

        binding.popularityArea.setOnClickListener {
            // 인기순 활성화
            binding.mainEclipse.visibility = View.INVISIBLE
            binding.mainText.setTextColor(Color.parseColor("#75777E"))
            binding.popularityEclipse.visibility = View.VISIBLE
            binding.popularityText.setTextColor(Color.parseColor("#000000"))
            binding.recentEclipse.visibility = View.INVISIBLE
            binding.recentText.setTextColor(Color.parseColor("#75777E"))
            sortByPopularity()
        }

        binding.recentArea.setOnClickListener {
            // 최신순 활성화
            binding.mainEclipse.visibility = View.INVISIBLE
            binding.mainText.setTextColor(Color.parseColor("#75777E"))
            binding.popularityEclipse.visibility = View.INVISIBLE
            binding.popularityText.setTextColor(Color.parseColor("#75777E"))
            binding.recentEclipse.visibility = View.VISIBLE
            binding.recentText.setTextColor(Color.parseColor("#000000"))
            sortByRecent()
        }

        return binding.root
    }

    fun sortByMain(){
        childFragmentManager.beginTransaction()
            .replace(com.example.kiding.R.id.sort_by_fragment, sortByMainFragment)
            .addToBackStack(null)
            .commit()
    }

    fun sortByPopularity(){
        childFragmentManager.beginTransaction()
            .replace(com.example.kiding.R.id.sort_by_fragment, sortByPopularityFragment)
            .addToBackStack(null)
            .commit()
    }

    fun sortByRecent(){
        childFragmentManager.beginTransaction()
            .replace(com.example.kiding.R.id.sort_by_fragment, sortByRecentFragment)
            .addToBackStack(null)
            .commit()
    }

}