package com.example.nasaapod.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapod.base.RemoteResponse
import com.example.nasaapod.data.remote.dto.NasaAPODDto
import com.example.nasaapod.data.remote.dto.NasaAPODItem
import com.example.nasaapod.domain.model.ApodItems
import com.example.nasaapod.domain.usecases.AstronomyPictureOfTheDayUseCase
import com.example.nasaapod.presentation.event.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaViewModel @Inject constructor(
    private val aPodUseCase: AstronomyPictureOfTheDayUseCase
):ViewModel() {

    data class ScreenViewState(
        val isLoading: Boolean = false,
        val aPodItems: ArrayList<NasaAPODItem>? = null,
        val errorString: String? = null,
        val currentItem: NasaAPODItem? = null
    )

    private val _uiState = MutableStateFlow((ScreenViewState()))
    val uiState: StateFlow<ScreenViewState> get() = _uiState

    //TODO we can use ViewModel to update state for UI event
    fun handleEvents(uiEvents: UiEvents) {
        when(uiEvents) {
            is UiEvents.UpdateCurrentItem -> {
                _uiState.update {
                    it.copy(
                        currentItem = uiEvents.item
                    )
                }
            }
        }
    }

    /**
     * Asynchronously fetch images using the ViewModel's scope.
     *
     * This function initiates the process of fetching images using the specified [viewModelScope].
     * It updates the UI state to indicate that data loading has started, makes a network request
     * to retrieve images, and updates the UI state accordingly based on the result of the request.
     *
     * @author - I541246
     */
    fun fetchImages() {
        val parameters = HashMap<String, String>()
        parameters["api_key"] = "Ze6NHrcenVZo2Ld2tlghSUlWjoaRwUYNwhUcTvFo"
        parameters["count"] = "10"
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = uiState.value.copy(
                isLoading = true,
            )
            when(val result = aPodUseCase.getAstronomyPictureOfTheDat(
                params = parameters
            )) {
                is RemoteResponse.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            aPodItems = result.value
                        )
                    }
                }
                is RemoteResponse.Failure -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorString = it.errorString
                        )
                    }
                }
            }
        }
    }
}