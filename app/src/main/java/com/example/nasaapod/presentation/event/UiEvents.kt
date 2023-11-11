package com.example.nasaapod.presentation.event

import com.example.nasaapod.data.remote.dto.NasaAPODItem

sealed class UiEvents {

    data class UpdateCurrentItem(val item: NasaAPODItem): UiEvents()
}
