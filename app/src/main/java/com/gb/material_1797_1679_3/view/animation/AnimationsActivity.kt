package com.gb.material_1797_1679_3.view.animation

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.ActivityAnimationsBinding
import com.gb.material_1797_1679_3.databinding.ActivityAnimationsExplodeBinding

class AnimationsActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimationsExplodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsExplodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = Adapter()
    }

    private fun explode(clickedView: View) {
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)
        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.duration = 1000
        TransitionManager.beginDelayedTransition(binding.recyclerView, explode)
        binding.recyclerView.adapter = null
    }
    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_recycler_item,
                    parent,
                    false
                ) as View
            )
        }
        override fun getItemCount(): Int {
            return 32
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}