package com.example.nalssi.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.compose.onPrimaryContainerDark
import com.example.compose.onPrimaryContainerLight
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
    val data = viewModel.listWeather.collectAsState().value

    val isOpenBottomSheet = remember { mutableStateOf(false) }

    Scaffold (
        topBar = { HomeTopBar(onClick = { isOpenBottomSheet.value = true }) },
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isOpenBottomSheet.value) {
                MyModalBottomSheet(isOpenState = isOpenBottomSheet, homeScreenCallback = homeScreenCallback)
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (data) {
                    is DataState.Error -> Text(data.toString())
                    is DataState.Loading -> Text(data.toString())
                    is DataState.Success, is DataState.Cached -> {
                        val data = when (data) {
                            is DataState.Success -> data.data
                            is DataState.Cached -> data.data
                            else -> emptyList()
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            if (data.isEmpty()) {
                                item {
                                    Column {
                                        Text("Empty")
                                    }
                                }
                            } else {
                                items(data.size) { index ->
                                    MyWeatherCard(
                                        weatherItem = data[index],
                                        onClick = { homeScreenCallback.onItemClicked(data[index]) },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyWeatherCard(
    weatherItem: WeatherItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(120.dp)
            .height(240.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(weatherItem.current?.condition?.icon?.replaceFirst("^//".toRegex(), "https://"))
                        .crossfade(true)
                        .build(),
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = "${weatherItem.current?.temp_c.toString()}°",
                    style = AppTypography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                )
                MyChip(text = weatherItem.current?.condition?.text.toString())
                Text(
                    text = weatherItem.location?.name.toString(),
                    style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MyChip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = onPrimaryContainerLight)
            .padding(vertical = 4.dp, horizontal = 8.dp),

    ) {
        Text(
            text = text,
            style = AppTypography.bodySmall.copy(color = onPrimaryContainerDark),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalBottomSheet(isOpenState: MutableState<Boolean>, homeScreenCallback: HomeScreenCallback) {
    ModalBottomSheet(
        onDismissRequest = { isOpenState.value = false },
        dragHandle = {},
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 24.dp ,bottom = 24.dp)
        ) {
            MyOptionBottomSheet(idIcon = R.drawable.ic_person ,text = "Profile", contentDescription = "about_page", onClick = { isOpenState.value = false; homeScreenCallback.onProfileClicked() })
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            MyOptionBottomSheet(idIcon = R.drawable.ic_menu ,text = "Favorite", contentDescription = "favorite_page", onClick = { isOpenState.value = false; homeScreenCallback.onFavoriteClicked() })
        }
    }
}

@Composable
fun MyOptionBottomSheet(idIcon: Int ,text: String, contentDescription: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = idIcon),
            contentDescription = text,
        )
        Text(
            text = text,
            style = AppTypography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { this.contentDescription = contentDescription },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            Image(painter = painterResource(id = R.drawable.nalssi), modifier = Modifier.size(64.dp), contentDescription = "Logo")
        },
        title = {
            Text(
                text = "Nalssi",
                style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier.clickable { onClick() }
            )
        }
    )
}