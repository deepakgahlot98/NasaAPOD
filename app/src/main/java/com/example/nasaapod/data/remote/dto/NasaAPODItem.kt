package com.example.nasaapod.data.remote.dto


import com.example.nasaapod.domain.model.ApodItemsItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NasaAPODItem(
    @SerialName("copyright")
    val copyright: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("explanation")
    val explanation: String? = null,
    @SerialName("hdurl")
    val hdurl: String? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    @SerialName("service_version")
    val serviceVersion: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("url")
    val url: String? = null
)

fun NasaAPODItem.toApodItemsItem(): ApodItemsItem {
    return ApodItemsItem(
        copyright = copyright,
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        mediaType = mediaType,
        serviceVersion = serviceVersion,
        title = title,
        url = url
    )
}