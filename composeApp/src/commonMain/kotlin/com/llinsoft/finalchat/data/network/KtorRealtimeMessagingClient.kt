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

class KtorRealtimeMessagingClient(
    private val httpClient: HttpClient
) : RealtimeMessagingClient {

    private companion object {
        const val WEB_SOCKET_URL = "ws://10.0.2.2:8080/getChatAnswer"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var session: WebSocketSession? = null
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
        scope.coroutineContext.cancelChildren()
    }

    private suspend fun ensureConnected() {
        if (session?.isActive == true) return

        session = httpClient.webSocketSession { url(WEB_SOCKET_URL) }
        session?.let { startMessageProcessing(it) }
    }

    private fun startMessageProcessing(session: WebSocketSession) {
        scope.launch {
            session
                .incoming
                .consumeAsFlow()
                .filterTextMessages()
                .catch { e -> handleError(e) }
                .collect { text -> _incomingMessages.emit(text) }
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