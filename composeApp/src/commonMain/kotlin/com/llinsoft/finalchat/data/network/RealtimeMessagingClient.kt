package com.llinsoft.finalchat.data.network

import kotlinx.coroutines.flow.Flow


/**
 * Interface representing a Realtime Messaging Client for communicating with the backend.
 */
interface RealtimeMessagingClient {
    fun getChatResponseStream(): Flow<String>
    /**
     * ***TODO add different points to access different APIs as a parameter***
     *
     * Sends a text request to the backend.
     * @param request The formatted text request to be sent.
     */
    suspend fun sendTextRequest(request: String)
    suspend fun close()
}