package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.io.IOException


class ServerActivity : AppCompatActivity() {
    private var server: MyHTTPServer? = null
    lateinit var textViewStatus: TextView
    lateinit var textViewData: TextView // For displaying the data
    private val handler = Handler(Looper.getMainLooper()) // To update UI
    //    private lateinit var startServerButton: MaterialButton
//    private lateinit var stopServerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)
//        startServerButton = findViewById(R.id.startServerButton)
//        stopServerButton = findViewById(R.id.stopServerButton)
        textViewStatus = findViewById(R.id.textViewStatus)
        textViewData = findViewById(R.id.textViewData)
}
        // Start the server when the button is clicked
//        startServerButton.setOnClickListener {
        // Method triggered when the button is clicked
        fun goToHomePage(view: View) {
            if (server == null) {
                try {
                    server = MyHTTPServer()
                    textViewStatus.text = getString(R.string.Server_Started)
                    updateDataDisplay()
                } catch (e: IOException) {
                    e.printStackTrace()
                    textViewStatus.text = getString(R.string.Server_Failed)
                    return // Do not navigate if the server fails to start
                }
            }
            // Server started successfully, now move to InfoPage_Activity
            val intent = Intent(this, InfoPage_Activity::class.java)
            startActivity(intent)
        }

        // Stop the server when the button is clicked
//        stopServerButton.setOnClickListener {
//            server?.stop()
//            server = null
//            textViewStatus.text = getString(R.string.Server_stopped)
//        }
//    }
    private fun updateDataDisplay() {
        handler.postDelayed({
            // Update the UI with the latest data
            textViewData.text = server?.latestData ?: "No data received"
            updateDataDisplay() // Continue updating periodically
        }, 1000) // Update every second
    }


    override fun onDestroy() {
        super.onDestroy()
        // Stop the server when the activity is destroyed
        server?.stop()
    }
}
