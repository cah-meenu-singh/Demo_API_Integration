package com.example.apiintegration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.apiintegration.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var btnGetStarted: Button

    private val backgrounds = listOf(
        R.drawable.bg1,
        R.drawable.bg2,
        R.drawable.bg3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.appRed)
        viewPager = findViewById(R.id.viewPager)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        btnGetStarted = findViewById(R.id.btnGetStarted)
        val adapter = OnboardingAdapter(backgrounds)
        viewPager.adapter = adapter

        updateButtonVisibility(0) // initial state

        btnPrevious.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        btnNext.setOnClickListener {
            if (viewPager.currentItem < backgrounds.lastIndex) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                // Navigate to next screen when last page reached
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, ToDoActivity::class.java))
            finish()
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonVisibility(position)
            }
        })
    }

    private fun updateButtonVisibility(position: Int) {
        when (position) {
            0 -> {
                btnPrevious.visibility = View.INVISIBLE
                btnNext.visibility = View.VISIBLE
                btnGetStarted.visibility = View.GONE
            }
            backgrounds.lastIndex -> {
                btnPrevious.visibility = View.VISIBLE
                btnNext.visibility = View.GONE
                btnGetStarted.visibility = View.VISIBLE
            }
            else -> {
                btnPrevious.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
                btnGetStarted.visibility = View.GONE
            }
        }
    }

}
