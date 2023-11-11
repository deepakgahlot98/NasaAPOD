package com.example.nasaapod.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.nasaapod.R
import com.example.nasaapod.presentation.viewmodel.NasaViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,)
@Composable
fun ImageListScreen(
    viewState: NasaViewModel.ScreenViewState
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        viewState.aPodItems?.toList()?.size?: 0
    }
    val coroutineScope = rememberCoroutineScope()
    var text = (viewState.aPodItems?.toList())?.get(pagerState.currentPage)?.title?:""

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = (viewState.aPodItems?.toList())?.get(pagerState.currentPage)?.explanation?:"No Explanation Provided",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = (viewState.aPodItems?.toList())?.get(pagerState.currentPage)?.copyright?:"No Copyright Information",
                    fontSize = 8.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = (viewState.aPodItems?.toList())?.get(pagerState.currentPage)?.date?:"No Date Provided",
                    fontSize = 8.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
        ) {
            Box() {
                HorizontalPager(
                    modifier = Modifier.align(Alignment.Center),
                    state = pagerState,
                    pageSpacing = 0.dp,
                    userScrollEnabled = true,
                    reverseLayout = false,
                    contentPadding = PaddingValues(0.dp),
                    beyondBoundsPageCount = 0,
                    key = {
                        (viewState.aPodItems?.toList())?.get(it)?.title?:""
                    }
                )
                {
                    ImageAndText(
                        imageUrl = (viewState.aPodItems?.toList())?.get(it)?.hdurl?:"",
                        contentDescription = (viewState.aPodItems?.toList())?.get(it)?.explanation?:"",
                        text = (viewState.aPodItems?.toList())?.get(it)?.title?:""
                    )
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, end = 16.dp)
                        .then(Modifier.size(32.dp)),
                    onClick = {
                        coroutineScope.launch {
                            // pagerState.scrollToPage(0)
                            pagerState.animateScrollToPage(pagerState.currentPage.dec())
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Previous Image",
                        tint = Color.Unspecified
                    )
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(start = 16.dp, end = 16.dp)
                        .then(Modifier.size(32.dp)),
                    onClick = {
                        coroutineScope.launch {
                            // pagerState.scrollToPage(0)
                            pagerState.animateScrollToPage(pagerState.currentPage.inc())
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "Next Image",
                        tint = Color.Unspecified
                    )
                }
                Icon(imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp)
                        .clickable {
                            if (modalSheetState.isVisible) {
                                coroutineScope.launch {
                                    modalSheetState.hide()
                                }
                            } else {
                                coroutineScope.launch {
                                    modalSheetState.show()
                                }
                            }
                        })
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    text: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        GlideImage(
        modifier = Modifier,
        model = imageUrl,
        contentScale = ContentScale.Crop,
        loading = placeholder {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        },
        contentDescription = contentDescription
    )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(alignment = Alignment.TopCenter),
            text = text,
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )

    }
}