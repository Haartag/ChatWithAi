package com.llinsoft.finalchat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class KtorRealtimeMessagingClient(
    private val httpClient: HttpClient
) : RealtimeMessagingClient {

    private companion object {
        const val BASE_WEB_SOCKET_URL = "ws://10.0.2.2:8080/chat"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var session: WebSocketSession? = null
    private var sessionId: String? = null
    private val _incomingMessages = MutableSharedFlow<String>()

    init {
        scope.launch {
            ensureConnected()
        }
    }

    override fun getChatResponseStream(): Flow<String> = _incomingMessages.asSharedFlow()

    override suspend fun sendTextRequest(request: String) {
        ensureConnected()
        session?.sendText(request)
    }

    override suspend fun close() {
        session?.close()
        session = null
        sessionId = null
        scope.coroutineContext.cancelChildren()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun generateSessionId() {
        if (sessionId.isNullOrEmpty()) {
            sessionId = Uuid.random().toString()
        }
    }

    private suspend fun ensureConnected() {
        if (session?.isActive == true) return

        generateSessionId()
        session = httpClient.webSocketSession {
            url("$BASE_WEB_SOCKET_URL?sessionId=${sessionId ?: throw IllegalStateException("No session ID")}")
        }
        session?.let { startMessageProcessing(it) }
    }

    private fun startMessageProcessing(session: WebSocketSession) {
        scope.launch {
            try {
                session
                    .incoming
                    .consumeAsFlow()
                    .filterTextMessages()
                    .catch { e -> handleError(e) }
                    .collect { text -> _incomingMessages.emit(text) }
            } catch (e: Exception) {
                handleError(e)
                close()
                ensureConnected() //Attempt reconnect
            }
        }
    }

    private fun Flow<Frame>.filterTextMessages() = this
        .filterIsInstance<Frame.Text>()
        .mapNotNull { it.readText() }

    private fun handleError(throwable: Throwable) {
        // TODO Error handling will be here
        println("###error###${throwable.message}")
    }

    private suspend fun WebSocketSession.sendText(text: String) {
        outgoing.send(Frame.Text(text))
    }
}