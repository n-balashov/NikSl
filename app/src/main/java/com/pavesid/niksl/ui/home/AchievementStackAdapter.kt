package com.pavesid.niksl.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pavesid.niksl.R
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.AchievementCardBinding

class AchievementStackAdapter() : RecyclerView.Adapter<AchievementStackAdapter.ViewHolder>() {

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
        holder.bind(position)
    }

    override fun getItemCount(): Int = achievements.size

    class ViewHolder(private val binding: AchievementCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) =
            when (position % 4) {
                0 -> binding.achieve.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.blue_inactive
                    )
                )
                1 -> binding.achieve.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.green_inactive
                    )
                )
                2 -> binding.achieve.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.orange_inactive
                    )
                )
                3 -> binding.achieve.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.purple_inactive
                    )
                )
                else -> binding.achieve.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.red_inactive
                    )
                )
            }
    }
}
