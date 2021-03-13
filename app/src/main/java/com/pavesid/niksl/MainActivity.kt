package com.pavesid.niksl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pavesid.niksl.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.pavesid.niksl.extensions.open

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.like -> {
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.home -> {
                    supportFragmentManager.open {
                        add(R.id.container, HomeFragment.newInstance(), null)
                    }
                    true
                }
                R.id.dislike -> {
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.like -> {
                    Toast.makeText(this, "Уже нажато", Toast.LENGTH_SHORT).show()
                }
                R.id.home -> {
                    Toast.makeText(this, "Уже нажато", Toast.LENGTH_SHORT).show()
                }
                R.id.dislike -> {
                    Toast.makeText(this, "Уже нажато", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
