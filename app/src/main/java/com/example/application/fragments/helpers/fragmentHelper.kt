package com.example.application.fragments.helpers

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun Fragment.replaceFragment(activity: AppCompatActivity, @IdRes containerId: Int) {
    activity.supportFragmentManager
        .beginTransaction()
        .replace(containerId, this)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .commit()
}
