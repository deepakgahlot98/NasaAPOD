package com.example.nasaapod.di

import com.example.nasaapod.data.remote.NasaApi
import com.example.nasaapod.data.repository.NasaRepositoryImp
import com.example.nasaapod.domain.repository.NasaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApodModule {

    @Provides
    @Singleton
    fun providesNasaApi(): NasaApi {
        val BASE_URL = "https://api.nasa.gov/"

        val client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        client.readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .callTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(NasaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNasaRepository(api: NasaApi): NasaRepository {
        return NasaRepositoryImp(api)
    }
}