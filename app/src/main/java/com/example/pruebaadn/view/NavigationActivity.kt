package com.example.pruebaadn.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.example.pruebaadn.databinding.ActivityNavigationBinding
import com.example.pruebaadn.view.insert_vehicle.InsertViewModel
import javax.inject.Inject

class NavigationActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationViewModel: NavigationViewModel
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (App.getContext() as App).getComponentApplication()?.inject(this)

        navigationViewModel.insertVariablesInit()
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}