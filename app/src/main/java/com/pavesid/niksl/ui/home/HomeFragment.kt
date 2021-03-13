package com.pavesid.niksl.ui.home

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pavesid.niksl.R
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentHomeBinding
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.ui.MainViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), CardStackListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val cardStackManager by lazy {
        CardStackLayoutManager(requireContext(), this@HomeFragment).apply {
            setStackFrom(StackFrom.Left)
            setVisibleCount(3)
            setScaleInterval(0.95f)
            setMaxDegree(20.0f)
            setDirections(Direction.HORIZONTAL)
            setSwipeThreshold(0.3f)
            setCanScrollHorizontal(true)
            setCanScrollVertical(false)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        }
    }

    private val viewModel: MainViewModel by viewModels()

    private lateinit var achievementAdapter: AchievementStackAdapter

    private lateinit var achievements: List<Achievement>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribe()
        setupButton()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        val id = achievements[cardStackManager.topPosition - 1].id
        viewModel.updateAchievement(direction?.name == "Right", id)
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    private fun initView() {
        achievementAdapter = AchievementStackAdapter()
        binding.cardStackView.apply {
            layoutManager = cardStackManager
            adapter = achievementAdapter
        }
    }

    private fun subscribe() {
        viewModel.notViewedAchievements.observe(this.viewLifecycleOwner) {
            achievements = it
            achievementAdapter.achievements = it
        }
    }

    private fun setupButton() {
        binding.notDoneButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }

        binding.doneButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
