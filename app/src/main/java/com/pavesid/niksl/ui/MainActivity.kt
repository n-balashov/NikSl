package com.pavesid.niksl.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pavesid.niksl.R
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.ActivityMainBinding
import com.pavesid.niksl.extensions.open
import com.pavesid.niksl.ui.detail.DetailFragment
import com.pavesid.niksl.ui.done.DoneFragment
import com.pavesid.niksl.ui.home.HomeFragment
import com.pavesid.niksl.ui.notyet.NotYetFragment
import dagger.hilt.android.AndroidEntryPoint
import nl.joery.animatedbottombar.AnimatedBottomBar

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState ?: supportFragmentManager.open {
            add(R.id.container, HomeFragment.newInstance(), null)
        }

        initView()
        subscribe()
    }

    private fun initView() {
        binding.bottomNavigation.setOnTabSelectListener(object :
            AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when (newIndex) {
                    0 -> {
                        supportFragmentManager.open {
                            add(R.id.container, NotYetFragment.newInstance(), null)
                        }
                    }
                    1 -> {
                        supportFragmentManager.open {
                            add(R.id.container, HomeFragment.newInstance(), null)
                        }
                    }
                    2 -> {
                        supportFragmentManager.open {
                            add(R.id.container, DoneFragment.newInstance(), null)
                        }
                    }
                }
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
            }
        })
    }

    private fun subscribe() {
        viewModel.apply {
            notViewedAchievements.observe(this@MainActivity) {
                binding.bottomNavigation.setBadgeAtTabIndex(
                    1,
                    AnimatedBottomBar.Badge(it.size.toString())
                )
            }
            doneAchievements.observe(this@MainActivity) {
                binding.bottomNavigation.setBadgeAtTabIndex(
                    2,
                    AnimatedBottomBar.Badge(it.size.toString())
                )
            }
            notYetAchievements.observe(this@MainActivity) {
                binding.bottomNavigation.setBadgeAtTabIndex(
                    0,
                    AnimatedBottomBar.Badge(it.size.toString())
                )
            }
        }
    }

    fun openDetails(achievement: Achievement) {
        supportFragmentManager.open {
            add(R.id.main, DetailFragment.newInstance(achievement), null).addToBackStack(null)
        }
    }
}
