package com.example.apiintegration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val backgrounds: List<Int>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBackground: ImageView = itemView.findViewById(R.id.imgBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.imgBackground.setImageResource(backgrounds[position])
    }

    override fun getItemCount() = backgrounds.size
}
