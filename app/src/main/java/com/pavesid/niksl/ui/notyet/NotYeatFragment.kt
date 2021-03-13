package com.pavesid.niksl.ui.notyet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.pavesid.niksl.R
import com.pavesid.niksl.core.OverlapDecoration
import com.pavesid.niksl.databinding.FragmentDoneBinding
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.ui.done.DoneViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotYeatFragment : Fragment(R.layout.fragment_done) {

    private val viewModel: DoneViewModel by viewModels()
    private val binding: FragmentDoneBinding by viewBinding(FragmentDoneBinding::bind)

    private lateinit var notYetAdapter: NotYetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribe()
    }

    private fun initView() {
        notYetAdapter = NotYetAdapter()
        val moviesLayoutManager = LinearLayoutManager(context, VERTICAL, false)
        binding.rvDone.apply {
            setHasFixedSize(true)
            layoutManager = moviesLayoutManager
            adapter = notYetAdapter

            addItemDecoration(OverlapDecoration())
        }
    }

    private fun subscribe() {
        viewModel.loadNotYetAchievements()
        viewModel.notYetAchievements.observe(this.viewLifecycleOwner) {
            notYetAdapter.achievements = it
        }
    }

    companion object {

        fun newInstance() = NotYeatFragment()
    }
}
