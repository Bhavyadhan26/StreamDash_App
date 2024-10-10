package com.example.loginapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_page)
        val backArrow: ImageView = findViewById(R.id.back_arrow)
        val contactUsButton: ImageButton = findViewById(R.id.contact_us)
        val homePageButton: ImageButton = findViewById(R.id.home_page)
        val MenuButton: ImageButton = findViewById(R.id.Menu)

        // Set click listener on the back arrow ImageView
        backArrow.setOnClickListener {
            onBackPressed() // This will take the user back to the previous activity
        }
        // Set onClickListeners
        contactUsButton.setOnClickListener {
            val intent = Intent(this, ContactPageActivity::class.java)
            startActivity(intent)

        }
        homePageButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }
        MenuButton.setOnClickListener {
            val intent = Intent(this, ContactPageActivity::class.java)
            startActivity(intent)
        }
    }

    // Method to handle click on the LinearLayout for calling
    fun callUs(view: android.view.View) {
        val phoneNumber = "+919681857475"
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No app available to handle this action", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to handle click on the button for sending email
    fun sendEmail(view: android.view.View) {
        val email = "bdhanuka26@gmail.com"
        val subject = "URGENT: Contact"
        val body = "Type your message below"

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // Specifies this is for email clients
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email)) // Recipient email address
            putExtra(Intent.EXTRA_SUBJECT, subject) // Subject of the email
            putExtra(Intent.EXTRA_TEXT, body) // Body of the email
        }

        // Verify if there is an email client available
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Choose an email client"))
        } else {
            Toast.makeText(this, "No email app available to send email", Toast.LENGTH_SHORT).show()
        }
    }
    fun openFacebook(view: View) {
        val url = "https://www.instagram.com/bhavyadhanuka_/" // Replace with your desired URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    fun openInstagram(view: View) {
        val url = "https://www.instagram.com/bhavyadhanuka_/" // Replace with your desired URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    fun openX(view: View) {
        val url = "https://x.com/BhavyaDhanuka" // Replace with your desired URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    fun openLinkedin(view: View) {
        val url = "https://www.linkedin.com/in/bhavya-dhanuka26-" // Replace with your desired URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}
