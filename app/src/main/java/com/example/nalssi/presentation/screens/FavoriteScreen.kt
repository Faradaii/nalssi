package com.example.nalssi.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nalssi.R
import com.example.nalssi.data.DataState
import com.example.nalssi.presentation.callback.FavoriteScreenCallback
import com.example.nalssi.presentation.callback.HomeScreenCallback
import com.example.nalssi.presentation.viewmodels.FavoriteViewModel
import com.example.nalssi.presentation.viewmodels.HomeViewModel
import com.example.nalssi.presentation.widget.MyWeatherCard
import com.example.ui.theme.AppTypography
import org.koin.androidx.compose.koinViewModel


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteScreenCallback: FavoriteScreenCallback,
) {
    val viewModel: FavoriteViewModel = koinViewModel()
    val data = viewModel.listWeather.collectAsState().value

    Scaffold (
        topBar = { FavoriteTopBar(onClick = { favoriteScreenCallback.onBackClicked() }) },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            if (data.isEmpty()) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                    Text("Empty")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(horizontal = 8.dp).fillMaxSize()
                ) {
                    items(data.size) { index ->
                        MyWeatherCard(
                            weatherItem = data[index],
                            onClick = { favoriteScreenCallback.onItemClicked(data[index]) },
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar(onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        },
        title = {
            Text(
                text = "Favorite",
                style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
        },
    )
}

