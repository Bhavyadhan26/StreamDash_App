package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity: AppCompatActivity() {
    private lateinit var profileTextView: TextView
    private lateinit var settingsTextView: TextView
    private lateinit var logoutTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_page)

        // Set up the close button to finish the activity and return to the previous screen
        val closeButton: View = findViewById(R.id.close_button)
        closeButton.setOnClickListener {
            finish() // Close this activity when the close button is clicked
        }
        profileTextView = findViewById(R.id.profile_btn)
        profileTextView.setOnClickListener {
            val intent = Intent(this, ProfilePageActivity::class.java)
            startActivity(intent)
        }
        settingsTextView = findViewById(R.id.settings_btn)
        settingsTextView.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        logoutTextView=findViewById(R.id.logout_btn)
        logoutTextView.setOnClickListener{
            Toast.makeText(this, getString(R.string.logout_successful), Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    // Handle back button press to finish the activity
    override fun onBackPressed() {
        finish() // Close this activity
    }
}