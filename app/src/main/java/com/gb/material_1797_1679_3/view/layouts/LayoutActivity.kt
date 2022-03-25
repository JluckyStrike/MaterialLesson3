package com.gb.material_1797_1679_3.view.layouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.ActivityLayoutBinding
import com.gb.material_1797_1679_3.view.layouts.constraint.ConstraintFragment
import com.gb.material_1797_1679_3.view.layouts.coordinator.CoordinatorFragment
import com.gb.material_1797_1679_3.view.layouts.motion.MotionFragment

class LayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()

    }

    private fun initBottomNavigationView() {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_constraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.bottom_coordinator -> {
                    navigationTo(CoordinatorFragment())
                    true
                }
                R.id.bottom_motion -> {
                    navigationTo(MotionFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_coordinator


        }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
            .replace(R.id.container_anim, f).commit()
    }

}