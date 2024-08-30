package com.example.newxtanimation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.newxtanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingItems: List<OnboardingItem>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingItems = listOf(
            OnboardingItem(
                title = "Bütün dərslərinizi bir mərkəzdən izləyin",
                description = "Dərslərinizi asanlıqla idarə edin, elanlar və resurslarla aktual qalın.",
                button = "Növbəti"
            ),
            OnboardingItem(
                title = "Davamiyyət izləmənizi sadələşdirin",
                description = "İnteqrasiya edilmiş təqvimimizlə davamiyyətinizi asanlıqla izləyin və cədvəlinizdən xəbərdar olun.",
                button = "Növbəti"
            ),
            OnboardingItem(
                title = "Yeniliklərdən vaxtında xəbərdar olun",
                description = "Tapşırıqlar, testlər, elanlar və digər vacib yeniləmələr üçün dərhal bildirişlər alın.",
                button = "Başla"
            )
        )

        // Setup ViewPager2
        val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }
            override fun getItemCount(): Int = onboardingItems.size
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val item = onboardingItems[position]
                holder.itemView.findViewById<TextView>(R.id.textTitle).text = item.title
                holder.itemView.findViewById<TextView>(R.id.textDescription).text = item.description
                holder.itemView.findViewById<TextView>(R.id.button).text = item.button
            }
        }

        binding.onBoardingViewPager.adapter = adapter

        // Setup indicators
        setupIndicators()
        binding.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })

        binding.button.setOnClickListener {
            val nextItem = binding.onBoardingViewPager.currentItem + 1
            if (nextItem < onboardingItems.size) {
                binding.onBoardingViewPager.setCurrentItem(nextItem, true)
            } else {
                // Handle the last page or action after onboarding
            }
        }
    }

    private fun setupIndicators() {
        val indicators = Array(onboardingItems.size) { ImageView(this) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        indicators.forEach { indicator ->
            indicator.layoutParams = layoutParams
            binding.indicatorsContainer.addView(indicator)
        }
        updateIndicators(0) // Set initial state
    }

    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorsContainer.childCount) {
            val indicator = binding.indicatorsContainer.getChildAt(i) as ImageView
            val drawableRes = if (i == position) {
                R.drawable.indicator_active_background
            } else {
                R.drawable.indicator_inactive_background
            }
            indicator.setImageDrawable(ContextCompat.getDrawable(this, drawableRes))
        }
    }
}
