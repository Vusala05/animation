package com.example.newxtanimation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newxtanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingItems: List<OnboardingItem>
    private var currentIndex = 0
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

        // Initially set the first onboarding item
        updateOnboardingContent()

        binding.button.setOnClickListener {
            currentIndex++
            if (currentIndex < onboardingItems.size) {
                updateOnboardingContent()
            } else {
                // Handle the last page or action after onboarding
            }
        }
    }

    private fun updateOnboardingContent() {
        val currentItem = onboardingItems[currentIndex]
        binding.textTitle.text = currentItem.title
        binding.textDescription.text = currentItem.description
        binding.button.text = currentItem.button
    }
}
