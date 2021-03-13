package com.pavesid.niksl.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pavesid.niksl.R
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribe()
    }

    private fun initViews() {
        binding.root.setOnClickListener {}
        binding.send.setOnClickListener { viewModel.writeMessage("Now - ${Date().time}") }
    }

    private fun subscribe() {
        viewModel.messages.observe(this.viewLifecycleOwner) {
            binding.text.text = it.toString()
        }
    }

    companion object {

        const val ACHIEVEMENT_ARG = "achievement"

        fun newInstance(achievement: Achievement): DetailFragment {
            val detailFragment = DetailFragment()
            val arguments = Bundle()
            arguments.putParcelable(ACHIEVEMENT_ARG, achievement)
            detailFragment.arguments = arguments
            return detailFragment
        }
    }
}
