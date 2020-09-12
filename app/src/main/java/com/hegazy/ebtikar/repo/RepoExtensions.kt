package com.hegazy.ebtikar.repo

import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants

fun APICallResult.Error<*>.toErrorMessage(): Int {
    return when (this.error) {
        NetworkConstants.unknownHostException -> R.string.unknown_host_exception_and_socket_timeout_exception_message
        NetworkConstants.socketTimeoutException -> R.string.unknown_host_exception_and_socket_timeout_exception_message
        NetworkConstants.sslHandshakeException -> R.string.ssl_handshake_exception_message
        NetworkConstants.clientSideError -> R.string.client_side_error_message
        NetworkConstants.serverSideError -> R.string.server_side_error_message
        NetworkConstants.forbidden -> R.string.api_query_limit_exceeded_error_message
        else -> R.string.generic_exception_and_generic_error_message
    }
}