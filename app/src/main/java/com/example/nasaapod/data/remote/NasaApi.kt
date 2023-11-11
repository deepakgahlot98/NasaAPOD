package com.example.nasaapod.data.remote

import com.example.nasaapod.data.remote.dto.NasaAPODDto
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NasaApi {

    @GET("planetary/apod")
    suspend fun getAstronomyPicturesOfTheDay(
        @QueryMap queryMap: HashMap<String, String>
    ): NasaAPODDto
}