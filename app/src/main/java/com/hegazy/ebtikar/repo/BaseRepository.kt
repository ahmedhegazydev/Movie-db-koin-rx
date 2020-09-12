package com.hegazy.ebtikar.repo


import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.clientSideError
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.forbidden
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.genericError
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.genericException
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.serverSideError
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.socketTimeoutException
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.sslHandshakeException
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.successWithoutBody
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants.unknownHostException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

open class BaseRepository {

    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): Any {
        try {

            val response = call.invoke()

            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    return APICallResult.Success(apiResponse) //a.k.a 200
                } ?: run {
                    return APICallResult.Success(successWithoutBody) //a.k.a 204
                }
            } else {
                when (response.code()) {
                    in 400..402, in 404..450 -> APICallResult.Error(clientSideError)
                    403 -> APICallResult.Error(forbidden)
                    in 500..598 -> APICallResult.Error(serverSideError)
                }
            }

        } catch (exception: Exception) {
            return when (exception) {
                is UnknownHostException -> APICallResult.Error(
                    unknownHostException
                )
                is SocketTimeoutException -> APICallResult.Error(socketTimeoutException)
                is SSLHandshakeException -> APICallResult.Error(sslHandshakeException)
                else -> APICallResult.Error(genericException)
            }
        }

        return APICallResult.Error(genericError)
    }
}