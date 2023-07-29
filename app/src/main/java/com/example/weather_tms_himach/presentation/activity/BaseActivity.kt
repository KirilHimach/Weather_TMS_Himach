package com.example.weather_tms_himach.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

internal class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()
    }

    internal fun setBottomNavViewVisibility(view: Int) {
        binding.bottomNavView.visibility = view
    }

    private fun initBottomNavigationView() {
        val bottomNavView: BottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        bottomNavView.setupWithNavController(navController)
    }
}