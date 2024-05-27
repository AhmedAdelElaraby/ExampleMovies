package com.workdev.marketsfood.utils

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    //    Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        remoteMessage.data.isNotEmpty().let {
          //  Log.d(TAG, "Message data payload: " + remoteMessage.data)
            // Handle message within 10 seconds
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            //Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
       // Log.d(TAG, "Refreshed token: $token")
        // Implement this method to send the new token to your server
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}