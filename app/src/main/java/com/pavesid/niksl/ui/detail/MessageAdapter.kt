package com.pavesid.niksl.ui.detail

import android.view.Gravity.LEFT
import android.view.Gravity.RIGHT
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.pavesid.niksl.data.model.Message
import com.pavesid.niksl.databinding.MessageItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    var account: GoogleSignInAccount? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    internal var messages: List<Message> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message, message.userId == account?.id)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message, fromMe: Boolean) {
            binding.apply {
                text.text = message.text
                val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy")
                date.text = sdf.format(Date(message.data))
                (date.layoutParams as LinearLayout.LayoutParams).gravity = if (fromMe) RIGHT else LEFT
                (text.layoutParams as LinearLayout.LayoutParams).marginStart = if (fromMe) 100 else 0
                (text.layoutParams as LinearLayout.LayoutParams).marginEnd = if (fromMe) 0 else 100
                text.gravity = if (fromMe) RIGHT else LEFT
            }
        }
    }
}
