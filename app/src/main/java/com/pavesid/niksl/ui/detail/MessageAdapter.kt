package com.pavesid.niksl.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavesid.niksl.data.model.Message
import com.pavesid.niksl.databinding.MessageItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    internal var messages: List<Message> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(messages[position])

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.apply {
                text.text = message.mes
                val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy")
                date.text = sdf.format(Date(message.data))
            }
        }
    }
}
