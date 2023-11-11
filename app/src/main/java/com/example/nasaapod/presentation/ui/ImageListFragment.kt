package com.example.nasaapod.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import com.example.nasaapod.presentation.viewmodel.NasaViewModel
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    private val viewModel: NasaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val extras = arguments
        viewModel.fetchImages()
        return ComposeView(requireContext()).apply {
            setContent {
                ImageListScreen(
                    viewState = viewModel.uiState.collectAsState().value
                )
            }
        }
    }
}