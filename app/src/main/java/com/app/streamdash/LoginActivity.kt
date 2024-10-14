package com.app.streamdash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var usernameInput:EditText
    lateinit var passwordInput:EditText
    lateinit var loginBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        usernameInput= findViewById(R.id.user_input)
        passwordInput= findViewById(R.id.password_input)
        loginBtn= findViewById(R.id.login_btn)


        val signUpText = findViewById<TextView>(R.id.SignUpText)  // Find the TextView

        signUpText.setOnClickListener {
            // Navigate to the login page here
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener()
        {
            val username=usernameInput.text.toString()
            val password=passwordInput.text.toString()

            val user = Database.login(username, password)
            if (user == null) {
                loginBtn.error = "Invalid Credentials!"
            } else {
                // Navigate to the new activity (HomeActivity)
                val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                startActivity(intent)
            }
        }
    }
    fun goToProfilePage(view: android.view.View) {
        val intent = Intent(this, ProfilePageActivity::class.java)
        startActivity(intent)
    }

}
