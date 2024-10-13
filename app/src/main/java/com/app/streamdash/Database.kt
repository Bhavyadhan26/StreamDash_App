package com.app.streamdash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest


data class User(val username: String, val email: String, val password: String, var deviceToken: String) {

    companion object {
       fun hashPassword(rawPassword: String): String {

            val raw = rawPassword.toByteArray()
            val digest = MessageDigest.getInstance("SHA-256")
            val hashed = digest.digest(raw).fold(StringBuilder(),
                { sb, it -> sb.append("%02x".format(it)) }
            ).toString()
            return hashed
        }
    }

}

object LoggedInUser {
    val user: MutableLiveData<User> by lazy {
       MutableLiveData<User>()
    }
}


object Database {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    private fun getUsers(): CollectionReference {
        return db.collection("users")
    }

    fun insertUser(username: String, email: String, rawPassword: String, deviceToken: String): User? {

        val hashed = User.hashPassword(rawPassword)

        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to hashed,
            "deviceToken" to deviceToken
        )

        val result = getUsers().document(username).set(user)
        if (result.isSuccessful) {
            User(username, email, hashed, deviceToken)
        }
        return null
    }

    fun getUser(username: String): User? {
        var user: User? = null
        if (username.isNotEmpty()) {
            runBlocking {
                val doc = getUsers().document(username).get().await()
                if (doc != null && doc.exists()) {
                    user = User(
                        username,
                        doc.data!!["email"].toString(),
                        doc.data!!["password"].toString(),
                        doc.data!!["deviceToken"].toString()
                    )
                }
            }
        }
        return user
    }

    fun login(username: String, rawPassword: String): User? {

        val password = User.hashPassword(rawPassword)
        val user = getUser(username)
        if (user != null && user.password == password) {
            LoggedInUser.user.postValue(user)
            return user
        }
        return null

    }

    fun updateToken(token: String) {
        if (LoggedInUser.user.isInitialized && LoggedInUser.user.value != null) {
            val user = LoggedInUser.user.value!!
            user.deviceToken = token
            getUsers().document(user.username).set(user)
            LoggedInUser.user.postValue(user)
        }
    }

}