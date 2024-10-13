package com.app.streamdash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ProfilePageActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        val name = findViewById<TextView>(R.id.ProfileUsername)
        val email = findViewById<TextView>(R.id.ProfileEmail)

        val user = LoggedInUser.user.value
        if (user != null) {

            name.text = user.username
            email.text = user.email
        }

        logoutButton = findViewById(R.id.logout_btn)

        logoutButton.setOnClickListener {
            LoggedInUser.user.postValue(null)
            Toast.makeText(this, getString(R.string.logout_successful), Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
