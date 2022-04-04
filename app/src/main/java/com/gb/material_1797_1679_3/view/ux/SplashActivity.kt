package com.gb.material_1797_1679_3.view.ux

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.view.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<ImageView>(R.id.iv).animate().rotationBy(720f).setDuration(4000L).start()

        val cdt = object : CountDownTimer(2000, 2000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
        cdt.start()
    }

}