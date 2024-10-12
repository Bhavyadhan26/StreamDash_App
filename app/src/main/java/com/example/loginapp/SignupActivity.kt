package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.R.id.main

class SignupActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var confirmPasswordInput: EditText
    lateinit var phoneInput: EditText
    private lateinit var signupBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        // Initialize views
        usernameInput = findViewById(R.id.user_input)
        emailInput = findViewById(R.id.user_email)
        passwordInput = findViewById(R.id.user_password)
        confirmPasswordInput = findViewById(R.id.user_conf_password)
        phoneInput = findViewById(R.id.user_contact)
        signupBtn = findViewById(R.id.signupbtn)


        // Initially disable confirm password and signup button
        confirmPasswordInput.isEnabled = false
        signupBtn.isEnabled = false

        signupBtn.setOnClickListener {
            registerUser()
        }

        val loginText = findViewById<TextView>(R.id.loginText)  // Find the TextView

        loginText.setOnClickListener {
            // Navigate to the login page here
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Real-time validation
        setupValidationListeners()
    }

    private fun setupValidationListeners() {
        // Enable confirm password field only after password is filled
        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = passwordInput.text.toString()
                confirmPasswordInput.isEnabled = password.isNotEmpty()
                validatePasswords()
                validateFields()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Check password match in real-time
        confirmPasswordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePasswords()
                validateFields()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Limit phone number to 10 digits
        phoneInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 10) {
                    phoneInput.error = null
                } else {
                    phoneInput.error = "Phone number must be 10 digits"
                }
                validateFields()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validate email format
        emailInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = emailInput.text.toString()
                if (!isValidEmail(email)) {
                    emailInput.error = "Invalid email address"
                } else {
                    emailInput.error = null
                }
                validateFields()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    private fun validatePasswords() {
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password != confirmPassword) {
                confirmPasswordInput.error = getString(R.string.error_passwords_not_matching)
            } else {
                confirmPasswordInput.error = null // Clear the error
            }
        }
    }

    private fun validateFields() {
        val isEmailValid = isValidEmail(emailInput.text.toString())
        val isPasswordValid = passwordInput.text.toString().isNotEmpty() &&
                passwordInput.text.toString() == confirmPasswordInput.text.toString()
        val isPhoneValid = phoneInput.text.toString().length == 10
        val isUsernameValid = usernameInput.text.toString().isNotEmpty()

        // Enable signup button only if all fields are valid
        signupBtn.isEnabled = isEmailValid && isPasswordValid && isPhoneValid && isUsernameValid
    }

    // Function to validate email format
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    // Function to handle registration logic
    private fun registerUser() {
//        Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
        Toast.makeText(this, getString(R.string.registration_successful), Toast.LENGTH_LONG).show()
    }
}