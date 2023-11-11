package com.example.nasaapod.base

import okhttp3.ResponseBody
import org.json.JSONObject

/**
 * @param T type of body when the response is [Success].
 */
sealed class RemoteResponse<out T> {

    data class Success<out T>(val value: T) : RemoteResponse<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val errorString: String?,
        val customErrorResId: Int? = null,
        var customError: JSONObject? = null,
        var customErrorList: List<JSONObject>? = null
    ): RemoteResponse<Nothing>()
}
