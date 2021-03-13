package com.pavesid.niksl.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.AchievementCardBinding

class AchievementStackAdapter : RecyclerView.Adapter<AchievementStackAdapter.ViewHolder>() {

    internal var achievements: List<Achievement> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AchievementCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(achievement = achievements[position])
    }

    override fun getItemCount(): Int = achievements.size

    class ViewHolder(private val binding: AchievementCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(achievement: Achievement) {
            binding.apply {
                text.text = achievement.name
                image.load(achievement.imagePath) {
                    crossfade(true)
                    placeholder(android.R.color.black)
                    transformations(RoundedCornersTransformation(8f, 8f, 0f, 0f))
                }
            }
        }
    }
}