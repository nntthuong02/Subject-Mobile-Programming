package com.app.demogmail

import EmailViewModel
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.demogmail.databinding.EmailItemBinding

class EmailAdapter(private var emails: List<Email>, private val viewModel: EmailViewModel) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(val binding: EmailItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = EmailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]

        with(holder.binding) {
            tvAvatar.text = email.senderName.first().toString()
            tvSender.text = email.senderName
            tvSubject.text = email.subject
            tvPreview.text = email.preview
            tvTime.text = email.time

            ivIcon.setImageResource(if (viewModel.isEmailStarred(position)) R.drawable.star_2__1_ else R.drawable.star_unsellected)

            ivIcon.setOnClickListener {
                viewModel.toggleStar(position)
                notifyItemChanged(position)
            }

            val isSelected = viewModel.isEmailSelected(position)
            icCheck.visibility = if (isSelected) View.VISIBLE else View.GONE
            cardView.setBackgroundResource(if (isSelected) R.drawable.card_background_selected else R.drawable.card_background_with_border)

            cardView.setOnClickListener {
                viewModel.toggleSelection(position)

                val isNowSelected = viewModel.isEmailSelected(position)
                icCheck.visibility = if (isNowSelected) View.VISIBLE else View.GONE
                cardView.setBackgroundResource(if (isNowSelected) R.drawable.card_background_selected else R.drawable.card_background_with_border)
            }
        }
    }

    override fun getItemCount(): Int = emails.size

    fun updateEmails(newEmails: List<Email>) {
        emails = newEmails
        notifyDataSetChanged()
    }
}
