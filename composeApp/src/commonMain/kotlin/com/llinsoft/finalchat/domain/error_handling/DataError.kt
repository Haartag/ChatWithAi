package com.llinsoft.finalchat.domain.error_handling

sealed interface DataError: Error {

    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: DataError {
        UNKNOWN
    }
}