package com.pavesid.niksl.ui.notyet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.NotyetItemBinding

internal class NotYetAdapter(
    private val doneListener: (Achievement) -> Unit
) : RecyclerView.Adapter<NotYetAdapter.AchievementViewHolder>() {

    internal var achievements: List<Achievement> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder =
        AchievementViewHolder(
            NotyetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            doneListener
        )

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) =
        holder.bind(achievements[position], position)

    override fun getItemCount(): Int = achievements.size

    class AchievementViewHolder(
        private val binding: NotyetItemBinding,
        private val doneListener: (Achievement) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(achievement: Achievement, position: Int) {
            binding.apply {
                card.rotation = if (position % 2 == 0) 12F else -12F
                text.text = achievement.name
                image.load(achievement.imagePath) {
                    crossfade(true)
                    placeholder(android.R.color.black)
                    transformations(RoundedCornersTransformation(8f))
                }
                btnDone.setOnClickListener { doneListener(achievement) }
            }
        }
    }
}
