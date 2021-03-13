package com.pavesid.niksl.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pavesid.niksl.R
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentHomeBinding
import com.pavesid.niksl.viewBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardStackView.apply {
            layoutManager = CardStackLayoutManager(requireContext()).apply {
                setStackFrom(StackFrom.Left)
                setVisibleCount(3)
                setScaleInterval(0.95f)
                setMaxDegree(20.0f)
                setDirections(Direction.HORIZONTAL)
                setSwipeThreshold(0.3f)
                setCanScrollHorizontal(true)
                setCanScrollVertical(false)
                setSwipeableMethod(SwipeableMethod.Manual)
            }
            adapter = AchievementStackAdapter(
                listOf(
                    Achievement(0L, "", ""),
                    Achievement(1L, "", ""),
                    Achievement(2L, "", ""),
                    Achievement(3L, "", ""),
                    Achievement(4L, "", ""),
                    Achievement(5L, "", "")
                )
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
