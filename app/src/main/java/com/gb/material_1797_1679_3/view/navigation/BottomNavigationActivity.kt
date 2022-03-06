package com.gb.material_1797_1679_3.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.ActivityBottomNavigationBinding
import com.google.android.material.badge.BadgeDrawable.TOP_START

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    navigationTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    navigationTo(MarsFragment())
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_mars)
                    true
                }
                R.id.bottom_view_system -> {
                    navigationTo(SystemFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_system
        badge.number = 99999
        badge.maxCharacterCount = 6
        badge.badgeGravity = TOP_START
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }

}