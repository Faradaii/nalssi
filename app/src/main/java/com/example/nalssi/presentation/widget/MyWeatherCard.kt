package com.example.nalssi.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.nalssi.domain.entities.weather.WeatherItem
import com.example.nalssi.core.theme.AppTypography

@Composable
fun MyWeatherCard(
    weatherItem: WeatherItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
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
                    text = "${weatherItem.current?.tempC.toString()}°",
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