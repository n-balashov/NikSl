package com.pavesid.niksl.ui.done

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.pavesid.niksl.R
import com.pavesid.niksl.databinding.FragmentDoneBinding
import com.pavesid.niksl.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoneFragment : Fragment(R.layout.fragment_done) {

    private val viewModel: DoneViewModel by viewModels()
    private val binding: FragmentDoneBinding by viewBinding(FragmentDoneBinding::bind)

    private lateinit var doneAdapter: DoneAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribe()
    }

    private fun initView() {
        doneAdapter = DoneAdapter()
        val moviesLayoutManager = LinearLayoutManager(context, VERTICAL, false)
        binding.rvDone.apply {
            setHasFixedSize(true)
            layoutManager = moviesLayoutManager
            adapter = doneAdapter

            addItemDecoration(OverlapDecoration())
        }
    }

    private fun subscribe() {
        viewModel.achievements.observe(this.viewLifecycleOwner) {
            doneAdapter.achievements = it
        }
    }

    companion object {

        fun newInstance() = DoneFragment()
    }
}
