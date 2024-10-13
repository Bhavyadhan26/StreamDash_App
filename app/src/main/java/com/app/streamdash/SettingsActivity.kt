package com.app.streamdash
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SettingsActivity : AppCompatActivity() {

    private lateinit var activityAlertsSwitch: Switch
    private lateinit var commentsSwitch: Switch
    private lateinit var remindersSwitch: Switch
    private lateinit var messagesSwitch: Switch
    private lateinit var saveButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settingspage)

        val backArrow: ImageView = findViewById(R.id.backButton)

        // Initialize switches and button
        activityAlertsSwitch = findViewById(R.id.activityAlertsSwitch)
        commentsSwitch = findViewById(R.id.commentsSwitch)
        remindersSwitch = findViewById(R.id.remindersSwitch)
        messagesSwitch = findViewById(R.id.messagesSwitch)
        saveButton = findViewById(R.id.saveButton)

        // Set click listener on the back arrow ImageView
        backArrow.setOnClickListener {
            onBackPressed() // This will take the user back to the previous activity
        }

        // Save button click listener
        saveButton.setOnClickListener {
            val message = "Changes Saved"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
