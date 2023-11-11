package com.example.nasaapod.data.repository

import com.example.nasaapod.data.remote.NasaApi
import com.example.nasaapod.data.remote.dto.NasaAPODDto
import com.example.nasaapod.domain.repository.NasaRepository
import javax.inject.Inject

class NasaRepositoryImp @Inject constructor(
    private val nasaApi: NasaApi
): NasaRepository {

    override suspend fun getAstronomyPicturesOfTheDay(
        params: HashMap<String, String>
    ): NasaAPODDto {
        return nasaApi.getAstronomyPicturesOfTheDay(
            queryMap = params
        )
    }
}