package com.pavesid.niksl

import androidx.fragment.app.Fragment
import com.pavesid.niksl.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
