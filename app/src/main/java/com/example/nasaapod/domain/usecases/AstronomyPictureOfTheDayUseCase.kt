package com.example.nasaapod.domain.usecases

import com.example.nasaapod.base.RemoteResponse
import com.example.nasaapod.data.remote.dto.NasaAPODDto
import com.example.nasaapod.domain.repository.NasaRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AstronomyPictureOfTheDayUseCase @Inject constructor(
    private val repository: NasaRepository
) {

    /**
     * @author - I541246
     */
    suspend fun getAstronomyPictureOfTheDat(params: HashMap<String, String>): RemoteResponse<NasaAPODDto> {
        try {
            return RemoteResponse.Success(
                repository.getAstronomyPicturesOfTheDay(
                    params = params
                )
            )
        } catch (e: HttpException) {
            return RemoteResponse.Failure(
                isNetworkError = false,
                errorString = e.localizedMessage ?: "An Unexpected error occurred",
                errorCode = e.code(),
                errorBody = e.response()?.errorBody()
            )
        } catch (e: IOException) {
            return RemoteResponse.Failure(
                isNetworkError = true,
                errorString = "Couldn't reach server. Check your internet connection",
                errorBody = null,
                errorCode = null,
            )
        }
    }
}