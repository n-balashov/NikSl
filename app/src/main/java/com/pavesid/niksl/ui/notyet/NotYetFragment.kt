package com.pavesid.niksl.ui.notyet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.pavesid.niksl.R
import com.pavesid.niksl.core.OverlapDecoration
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.databinding.FragmentDoneBinding
import com.pavesid.niksl.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotYetFragment : Fragment(R.layout.fragment_done) {

    private val viewModel: MainViewModel by viewModels()

    private val binding: FragmentDoneBinding by viewBinding(FragmentDoneBinding::bind)

    private lateinit var notYetAdapter: NotYetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribe()
    }

    private fun initView() {
        notYetAdapter = NotYetAdapter {
            viewModel.updateAchievement(true, it.id)
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
        }
        val moviesLayoutManager = LinearLayoutManager(context, VERTICAL, false)
        binding.rvDone.apply {
            setHasFixedSize(true)
            layoutManager = moviesLayoutManager
            adapter = notYetAdapter

            addItemDecoration(OverlapDecoration())
        }
    }

    private fun subscribe() {
        viewModel.notYetAchievements.observe(this.viewLifecycleOwner) {
            notYetAdapter.updateData(it)
        }
    }

    companion object {

        fun newInstance() = NotYetFragment()
    }
}
