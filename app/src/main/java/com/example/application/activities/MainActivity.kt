package com.example.application.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.application.R
import com.example.application.fragments.date.DateCalcFragment
import com.example.application.fragments.helpers.replaceFragment
import com.example.application.fragments.programmer.ProgrammerCalcFragment
import com.example.application.fragments.scientific.ScientificCalcFragment
import com.example.application.fragments.standard.StandardCalcFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var standardCalcFragment: StandardCalcFragment
    private lateinit var scientificCalcFragment: ScientificCalcFragment
    private lateinit var programmerCalcFragment: ProgrammerCalcFragment
    private lateinit var dateCalcFragment: DateCalcFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        //Initialize xml
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Toolbar set up
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            (R.string.open),
            (R.string.close)
        ) {


        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //initial fragment
        standardCalcFragment = StandardCalcFragment()
        standardCalcFragment.replaceFragment(this, R.id.fragment_container)

    }

    //Menu Item click
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_standard_calc -> {
                standardCalcFragment.replaceFragment(this, R.id.fragment_container)
            }
            R.id.nav_scientific_calc -> {
                scientificCalcFragment =
                    ScientificCalcFragment()
                scientificCalcFragment.replaceFragment(this, R.id.fragment_container)
            }
            R.id.nav_programmer_calc -> {
                programmerCalcFragment =
                    ProgrammerCalcFragment()
                programmerCalcFragment.replaceFragment(this, R.id.fragment_container)
            }
            R.id.nav_date_calc -> {
                dateCalcFragment = DateCalcFragment()
                dateCalcFragment.replaceFragment(this, R.id.fragment_container)
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}










