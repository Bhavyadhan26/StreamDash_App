package com.example.loginapp

import fi.iki.elonen.NanoHTTPD
import java.io.IOException

class MyHTTPServer : NanoHTTPD(8080) {

    var latestData: String = "No data received" // Store the latest received data

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
            Method.GET -> newFixedLengthResponse("Server Running") // Default response for GET
            else -> newFixedLengthResponse(Response.Status.METHOD_NOT_ALLOWED, "text/plain", "Method not allowed")
        }
    }

    // Handle POST requests specifically
    private fun handlePostRequest(session: IHTTPSession): Response {
        return try {
            val postData = parsePostData(session)
            if (postData.isNotEmpty()) {
                latestData = postData // Update latestData to be displayed in the app
                newFixedLengthResponse(Response.Status.OK, "text/plain", "Data received: $postData")
            } else {
                newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/plain", "No data received")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "Error receiving data")
        }
    }

    // Extract POST data from the request, this will get key-value pairs
    private fun parsePostData(session: IHTTPSession): String {
        val postData = StringBuilder()
        session.parseBody(mutableMapOf()) // Parse the body
        val parameters = session.parameters

        for ((key, valueList) in parameters) {
            if (valueList.isNotEmpty()) {
                postData.append("$key=${valueList.joinToString()}\n") // Append key=value pairs
            }
        }

        return postData.toString().trim() // Return the parsed data
    }
}
