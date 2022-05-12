package com.example.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ad340.forecast.CurrentForecastFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController: NavController = navHostFragment.navController

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setTitle(R.string.app_name)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingDialog(this, tempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


