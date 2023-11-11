package com.example.nasaapod.repository

import com.example.nasaapod.data.remote.dto.NasaAPODDto
import com.example.nasaapod.data.remote.dto.NasaAPODItem
import com.example.nasaapod.domain.repository.NasaRepository
import java.util.ArrayList

class FakeNasaRepository : NasaRepository {

    private val item1 = NasaAPODItem(
        copyright = "© NASA",
        date = "2023-11-09",
        explanation = "An example explanation for the first item.",
        hdurl = "https://example.com/image1_hd.jpg",
        mediaType = "image",
        serviceVersion = "v1",
        title = "Example Image 1",
        url = "https://example.com/image1.jpg"
    )

    private val item2 = NasaAPODItem(
    copyright = "© NASA",
    date = "2023-11-08",
    explanation = "An example explanation for the second item.",
    hdurl = "https://example.com/image2_hd.jpg",
    mediaType = "image",
    serviceVersion = "v1",
    title = "Example Image 2",
    url = "https://example.com/image2.jpg"
    )

    private val item3 = NasaAPODItem(
    copyright = "© NASA",
    date = "2023-11-08",
    explanation = "An example explanation for the third item.",
    hdurl = "https://example.com/image3_hd.jpg",
    mediaType = "image",
    serviceVersion = "v1",
    title = "Example Image 3",
    url = "https://example.com/image3.jpg"
    )

    private val items = ArrayList<NasaAPODItem>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getAstronomyPicturesOfTheDay(params: HashMap<String, String>): NasaAPODDto {
        items.add(item1)
        items.add(item2)
        items.add(item3)
        return NasaAPODDto(items)
    }
}