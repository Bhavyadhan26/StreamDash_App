package com.example.loginapp

import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import fi.iki.elonen.NanoHTTPD
import java.io.IOException
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.LongAsStringSerializer

@Serializable
data class Payload(
    val sensor: String,
    val value: Long
)

class HTTPServer(private val activity: AppCompatActivity):NanoHTTPD(8080) {

    private var sensors: HashMap<String, CardView> = HashMap() // map the sensor name to it's respective CardView id

    init {
        try {
            start(SOCKET_READ_TIMEOUT, false)
            println("Server started on port 8080")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Handle incoming requests
    override fun serve(session: IHTTPSession?): Response {
        return when (session?.method) {
            Method.POST -> handlePostRequest(session)
            else -> newFixedLengthResponse(
                Response.Status.METHOD_NOT_ALLOWED,
                "text/plain",
                "Method not allowed"
            )
        }
    }

    private fun handlePostRequest(session: IHTTPSession): Response {
        val data = HashMap<String, String>()
        session.parseBody(data)
        val raw = data["postData"]
        val parsed = Json.decodeFromString<Payload>(raw!!)
        this.activity.runOnUiThread {

                val cards = this.activity.findViewById<ViewGroup>(R.id.cardsLayout)
                val card: CardView
                val inflater = this.activity.layoutInflater
                card = if (this.sensors.contains(parsed.sensor)) {
                    this.sensors[parsed.sensor]!!
                } else {
                    inflater.inflate(R.layout.sensor_card, cards, false) as CardView
                }
                if (cards.indexOfChild(card) == -1) {
                    cards.addView(card, 0)
                }
                val text: TextView = card.getChildAt(0) as TextView
                text.text = "${parsed.sensor} Sensor\n\n${parsed.value}"
                this.sensors[parsed.sensor] = card
        }


        return newFixedLengthResponse(Response.Status.OK, "text/plain", "Payload Received")
    }


}
