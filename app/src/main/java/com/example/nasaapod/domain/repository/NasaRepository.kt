package com.example.nasaapod.domain.repository

import com.example.nasaapod.data.remote.dto.NasaAPODDto

interface NasaRepository {

    suspend fun getAstronomyPicturesOfTheDay(
        params: HashMap<String, String>
    ): NasaAPODDto
}