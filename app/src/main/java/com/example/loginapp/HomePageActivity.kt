package com.example.loginapp
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class HomePageActivity : AppCompatActivity() {

    private var backPressedOnce = false // Track if back is pressed once

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HTTPServer(this)
        setContentView(R.layout.homepage) // Replace with your actual layout file name

        // Get the current date
        val currentDate = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(Date())

        // Find the TextView by ID and set the current date
        val dateText: TextView = findViewById(R.id.dateText)
        dateText.text = currentDate
    }
    override fun onBackPressed() {
        if (backPressedOnce) {
            finishAffinity() // This will close all activities and exit the app
            return
        }
        this.backPressedOnce = true
        Toast.makeText(this, "Press once more to exit the app", Toast.LENGTH_SHORT).show()
        // Reset the backPressedOnce flag after 5 seconds
        android.os.Handler().postDelayed({ backPressedOnce = false }, 5000)
    }
}
