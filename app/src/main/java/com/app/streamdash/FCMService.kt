package com.app.streamdash

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


object SensorUpdateEvent {
    val payload: MutableLiveData<Payload> by lazy {
        MutableLiveData<Payload>()
    }
}


public class FCMService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        SensorUpdateEvent.payload.postValue(Payload(remoteMessage.data["sensor"]!!, remoteMessage.data["value"]!!.toLong()))
    }

}