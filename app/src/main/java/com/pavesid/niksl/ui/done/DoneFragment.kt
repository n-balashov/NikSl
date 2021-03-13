package com.pavesid.niksl.ui.done

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.pavesid.niksl.R
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentDoneBinding
import com.pavesid.niksl.viewBinding

class DoneFragment : Fragment(R.layout.fragment_done) {

    private val viewModel: DoneViewModel by viewModels()
    private val binding: FragmentDoneBinding by viewBinding(FragmentDoneBinding::bind)

    private lateinit var doneAdapter: DoneAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        doneAdapter = DoneAdapter()
        doneAdapter.achievements =
            listOf(
                Achievement(0, "Test #1", "https://i.ytimg.com/vi/cjCDbJ87LNY/hqdefault.jpg"),
                Achievement(1, "Test #2", "https://sun9-40.userapi.com/C2PzlqCGVDXJbYpahxSqUAKPnFcXC_eAHtRbsw/Y0j0tQv5IHw.jpg"),
                Achievement(2, "Test #3", "https://ubisoft-avatars.akamaized.net/1447ff64-7808-4b5c-807c-eb5551e9c44e/default_256_256.png"),
                Achievement(3, "Test #4", "https://i.pinimg.com/236x/a3/77/14/a3771445b13b06c80545c895a3c87eee.jpg"),
                Achievement(4, "Test #5", "https://hg1.funnyjunk.com/thumbnails/comments/Quotvlentinequot+_dd33b989ad2d25a2a9d52406962bf616.jpg"),
                Achievement(5, "Test #6", "https://www.meme-arsenal.com/memes/d24a7936f94557f614e33eb7ccec2103.jpg")
            )

        val moviesLayoutManager = LinearLayoutManager(context, VERTICAL, false)

        binding.rvDone.apply {
            setHasFixedSize(true)
            layoutManager = moviesLayoutManager
            adapter = doneAdapter

            addItemDecoration(OverlapDecoration())
        }
    }

    companion object {

        fun newInstance() = DoneFragment()
    }
}
