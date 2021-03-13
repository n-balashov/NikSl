package com.pavesid.niksl.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.pavesid.niksl.R
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)

    private lateinit var messageAdapter: MessageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribe()
    }

    private fun initViews() {
        binding.apply {
            root.setOnClickListener {}
            send.setOnClickListener {
                if (etMessage.text.toString().isNotEmpty()) {
                    viewModel.writeMessage(etMessage.text.toString())
                    etMessage.text.clear()
                }
            }

            messageAdapter = MessageAdapter()
            rvMessages.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = messageAdapter
            }
        }
    }

    private fun subscribe() {
        viewModel.messages.observe(this.viewLifecycleOwner) {
            messageAdapter.messages = it
        }

        binding.apply {
            achieveLayout.text.text = viewModel.achievement.name
            achieveLayout.image.load(viewModel.achievement.imagePath)
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
