package com.soenadiwai.getgo

import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.soenadiwai.getgo.databinding.ActivityMainBinding
import com.soenadiwai.getgo.ui.SearchResultFragment
import com.soenadiwai.getgo.ui.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment, tag)
            .addToBackStack("DashboardFragment")
            .commit()
    }

    override fun onBackPressed() {
        this.supportActionBar?.setDisplayHomeAsUpEnabled(false);
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment is SearchResultFragment){
            loadHomeFragment(DashboardFragment(),"DashboardFragment")
        } else{
            if (doubleBackToExitPressedOnce) {
                finishAffinity()
                System.exit(1)
            }
            this.doubleBackToExitPressedOnce = true
            val snackbar = Snackbar.make(this!!.currentFocus!!, "Press BACK again to exit", Snackbar.LENGTH_SHORT)
            snackbar.show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        }

    }

    fun loadHomeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment, tag)
            .addToBackStack("null")
            .commit()
    }

}