package com.example.nasaapod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.nasaapod.presentation.ui.ImageListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ImageListFragment()
        loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment, bundle: Bundle? = null) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (!supportFragmentManager.isDestroyed) {
            fragment.arguments = bundle
            transaction.replace(R.id.flFragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            //TODO to handle the error
        }
    }
}