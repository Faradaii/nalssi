package com.example.nalssi.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.nalssi.R
import com.example.nalssi.core.utils.WeatherHelper
import com.example.nalssi.data.DataState
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.presentation.callback.DetailScreenCallback
import com.example.nalssi.presentation.viewmodels.DetailViewModel
import com.example.nalssi.presentation.widget.MyInfoCard
import com.example.ui.theme.AppTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailScreenCallback: DetailScreenCallback,
    q: String
) {
    val viewModel: DetailViewModel = koinViewModel()
    viewModel.fetchDetailWeather(q)
    val weatherData: Any? = when (val data = viewModel.detailWeather.collectAsState().value) {
        is DataState.Error -> data.toString()
        is DataState.Loading -> data.toString()
        is DataState.Success, is DataState.Cached -> {
            val w = when (data) {
                is DataState.Success -> data.data
                is DataState.Cached -> data.data
                else -> null
            }
            w
        }
    }

    val forceRefresh = { viewModel.fetchDetailWeather(q, true) }

    val toggleFavorite = {
        if (weatherData is WeatherItem) viewModel.toggleFavoriteWeather(weatherData)
    }

    Scaffold(
        topBar = { DetailTopBar(title = q, onClick = { detailScreenCallback.onBackClicked() }, onRefresh = { forceRefresh() })},
        floatingActionButton = { FloatingActionButton(
            onClick = { toggleFavorite() }
        ) {
            Icon(
                painter = painterResource(id =
                if (weatherData is WeatherItem && weatherData.isFavorite) R.drawable.ic_favorite
                else R.drawable.ic_unfavorite),
                contentDescription = "Favorite")
        } }
    ) { innerPadding ->
        Box (
            modifier = modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (weatherData is WeatherItem){
                ContentDetail(weatherData)
            } else {
                Text(text = weatherData.toString())
            }
        }
    }
}

@Composable
fun ContentDetail(weather: WeatherItem) {
    val items = listOf(
        Triple(R.drawable.ic_speed,"Wind Speed", "${weather.current?.wind_mph} mph"),
        Triple(R.drawable.ic_winddir,"Wind Direction",
            WeatherHelper.getReadableDirection(weather.current?.wind_dir.toString())
        ),
        Triple(R.drawable.ic_pressure,"Pressure", "${weather.current?.pressure_mb} mbar"),
        Triple(R.drawable.ic_precipitation,"Precipitation", "${weather.current?.precip_mm}%"),
        Triple(R.drawable.ic_humidity,"Humidity", "${weather.current?.humidity}%"),
        Triple(R.drawable.ic_cloud,"Cloud", "${weather.current?.cloud}%"),
        Triple(R.drawable.ic_uv, "UV", "${weather.current?.uv}"),
        Triple(R.drawable.ic_time, "Timezone", "${weather.location?.timezone}")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
        userScrollEnabled = false
    ) {
        item (span = { GridItemSpan(2) }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(weather.current?.condition?.icon?.replaceFirst("^//".toRegex(), "https://"))
                    .crossfade(true)
                    .size(240)
                    .build(),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(240.dp),
            )
        }
        item (span = { GridItemSpan(2) }) {
            Text(
                text = "${weather.current?.temp_c.toString()}°",
                style = AppTypography.displayLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
            )
        }
        item (span = { GridItemSpan(2) }) {
            Text(
                text = weather.current?.condition?.text.toString(),
                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.Medium, textAlign = TextAlign.Center),
            )
        }
        items (items.size) { index ->
            MyInfoCard(
                icon = items[index].first,
                title = items[index].second,
                subtitle = items[index].third,
            )
        }
        item (span = { GridItemSpan(2) }) {
            Text(
                text = "Last updated at : ${weather.current?.lastUpdated}",
                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.Medium, textAlign = TextAlign.Center),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(title: String, onClick: () -> Unit, onRefresh: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(title.substringBefore("-"), style = AppTypography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        },
        actions = {
            IconButton(onClick = onRefresh) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_refresh),
                    contentDescription = "Back",
                )
            }
        }
    )
}