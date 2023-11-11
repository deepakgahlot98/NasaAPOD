package com.example.nasaapod.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasaapod.MainCoroutineRule
import com.example.nasaapod.domain.usecases.AstronomyPictureOfTheDayUseCase
import com.example.nasaapod.repository.FakeNasaRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.ApiStatus.Experimental
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NasaViewModelTest {
    private lateinit var viewModel: NasaViewModel
    private lateinit var repository: FakeNasaRepository
    private lateinit var fakeUseCase: AstronomyPictureOfTheDayUseCase

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = FakeNasaRepository()
        fakeUseCase = AstronomyPictureOfTheDayUseCase(repository)
        viewModel = NasaViewModel(fakeUseCase)
        viewModel.fetchImages()
    }

    @Test
    fun `get image list`() {
        Thread.sleep(2000)
        assertThat(viewModel.uiState.value.aPodItems?.size).isEqualTo(0)
    }
}