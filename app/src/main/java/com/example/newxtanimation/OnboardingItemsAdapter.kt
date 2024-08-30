package com.example.newxtanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newxtanimation.databinding.OnboardingItemContainerBinding

class OnboardingItemsAdapter(private val onboardingItems: List<OnboardingItem>) : RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container, parent, false)
        return OnboardingItemViewHolder(view)
    }

    override fun getItemCount(): Int = onboardingItems.size

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        // No need to bind specific views in this case
    }
}
