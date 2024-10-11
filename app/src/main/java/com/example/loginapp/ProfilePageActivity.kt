package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ProfilePageActivity : AppCompatActivity() {

    lateinit var logout_Btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        logout_Btn= findViewById(R.id.logout_btn)

        logout_Btn.setOnClickListener {
            Toast.makeText(this, getString(R.string.logout_successful), Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
