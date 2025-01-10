package com.example.nalssi.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nalssi.R
import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.HomeScreenCallback
import com.example.nalssi.presentation.viewmodels.HomeViewModel
import com.example.ui.theme.AppTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenCallback: HomeScreenCallback,
) {
    val viewModel: HomeViewModel = koinViewModel()
    val listWeather = viewModel.listWeather.collectAsState().value

    val isOpenBottomSheet = remember { mutableStateOf(false) }

    Scaffold (
        topBar = { HomeTopBar(onClick = { isOpenBottomSheet.value = true }) },
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            if (isOpenBottomSheet.value) {
                MyModalBottomSheet(isOpenState = isOpenBottomSheet, homeScreenCallback = homeScreenCallback)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (listWeather) {
                    is DataState.Error -> Text(listWeather.toString())
                    is DataState.Loading -> Text(listWeather.toString())
                    is DataState.Success, is DataState.Cached -> {
                        val data = when (listWeather) {
                            is DataState.Success -> listWeather.data
                            is DataState.Cached -> listWeather.data
                            else -> emptyList()
                        }
                        LazyColumn {
                            if (data.isEmpty()) {
                                item {
                                    Column {
                                        Text("Empty")
                                    }
                                }
                            } else {
                                items(data.size) { index ->
                                    Text(data[index].location?.name.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalBottomSheet(isOpenState: MutableState<Boolean>, homeScreenCallback: HomeScreenCallback) {
    ModalBottomSheet(
        onDismissRequest = { isOpenState.value = false },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            MyOptionBottomSheet(text = "Profile", onClick = { isOpenState.value = false; homeScreenCallback.onProfileClicked() })
            HorizontalDivider()
            MyOptionBottomSheet(text = "Favorite", onClick = { isOpenState.value = false; homeScreenCallback.onFavoriteClicked() })
            HorizontalDivider()
            MyOptionBottomSheet(text = "Settings", onClick = { isOpenState.value = false; homeScreenCallback.onSettingsClicked() })
        }
    }
}

@Composable
fun MyOptionBottomSheet(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = AppTypography.titleMedium.copy(textAlign = TextAlign.Center),
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text("Nalssi") },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier.clickable { onClick() }
            )
        }
    )
}
