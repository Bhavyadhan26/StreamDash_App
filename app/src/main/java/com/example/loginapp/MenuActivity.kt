package com.example.loginapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_page)

        // Set up the close button to finish the activity and return to the previous screen
        val closeButton: View = findViewById(R.id.close_button)
        closeButton.setOnClickListener {
            finish() // Close this activity when the close button is clicked
        }
    }
    // Handle back button press to finish the activity
    override fun onBackPressed() {
        finish() // Close this activity
    }
}