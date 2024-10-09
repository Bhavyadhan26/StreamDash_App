package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput:EditText
    lateinit var passwordInput:EditText
    lateinit var loginBtn: Button
    lateinit var OpenServerActivityButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactUsButton: ImageButton = findViewById(R.id.contact_us)
        val homePageButton: ImageButton = findViewById(R.id.home_page)
        val MenuButton: ImageButton = findViewById(R.id.Menu)
        usernameInput= findViewById(R.id.user_input)
        passwordInput= findViewById(R.id.password_input)
        loginBtn= findViewById(R.id.login_btn)
        OpenServerActivityButton=findViewById(R.id.openServerActivityButton)

        // Set onClickListeners
        contactUsButton.setOnClickListener {
            val intent = Intent(this, ContactPageActivity::class.java)
            startActivity(intent)

        }
        homePageButton.setOnClickListener {
            val intent = Intent(this, InfoPage_Activity::class.java)
            startActivity(intent)
        }
        MenuButton.setOnClickListener {
            val intent = Intent(this, ContactPageActivity::class.java)
            startActivity(intent)
        }
        OpenServerActivityButton.setOnClickListener {
            val intent = Intent(this, ServerActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener()
        {
            val username=usernameInput.text.toString()
            val password=passwordInput.text.toString()
            Log.i("Test Credentials", "Username: $username and Password : $password" )

           // Navigate to the new activity (HomeActivity)
            val intent = Intent(this@MainActivity, InfoPage_Activity::class.java)
            startActivity(intent)
        }
    }
    fun goToContactPage(view: android.view.View) {
        val intent = Intent(this, ContactPageActivity::class.java)
        startActivity(intent)
    }
}
