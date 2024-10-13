package com.app.streamdash

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Constraints.TAG
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer


@Serializable
data class Payload(
    val sensor: String,
    val value: Long
)



class HomePageActivity : AppCompatActivity() {

    private var backPressedOnce = false // Track if back is pressed once
    private var sensors: HashMap<String, CardView> = HashMap()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchRegistrationToken()
        setContentView(R.layout.homepage) // Replace with your actual layout file name

        SensorUpdateEvent.payload.observe(this, Observer<Payload> {payload -> this.onSensorUpdate(payload)})

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

    private fun fetchRegistrationToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

        })
    }
    private fun subscribeSensorUpdates() {
        Firebase.messaging.subscribeToTopic("sensor")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to sensor updates!"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }

    private fun onSensorUpdate(payload: Payload) {
        val cards = this.findViewById<ViewGroup>(R.id.cardsLayout)
        val card: CardView
        val inflater = this.layoutInflater
        card = if (this.sensors.contains(payload.sensor)) {
            this.sensors[payload.sensor]!!
        } else {
            inflater.inflate(R.layout.sensor_card, cards, false) as CardView
        }
        if (cards.indexOfChild(card) == -1) {
            cards.addView(card, 0)
        }
        val text: TextView = card.getChildAt(0) as TextView
        text.text = getString(R.string.sensor, payload.sensor, payload.value)
        this.sensors[payload.sensor] = card
    }
}
