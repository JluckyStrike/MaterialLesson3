package com.gb.material_1797_1679_3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.view.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                PictureOfTheDayFragment.newInstance()
            ).commit()
        }
    }

}