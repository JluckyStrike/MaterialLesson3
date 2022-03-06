package com.gb.material_1797_1679_3.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.BottomNavigationLayoutBinding
import com.gb.material_1797_1679_3.view.layouts.LayoutActivity
import com.gb.material_1797_1679_3.view.navigation.BottomNavigationActivity
import com.gb.material_1797_1679_3.view.navigation.NavigationActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_one->{
                    startActivity(Intent(requireContext(),NavigationActivity::class.java))
                }
                R.id.navigation_two->{
                    startActivity(Intent(requireContext(), BottomNavigationActivity::class.java))
                }
                R.id.navigation_three->{
                    startActivity(Intent(requireContext(), LayoutActivity::class.java))
                }
            }
            true
        }
    }

}