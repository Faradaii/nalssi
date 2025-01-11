package com.example.nalssi.presentation.screens

import android.inputmethodservice.Keyboard
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.nalssi.presentation.widget.MyCenter
import com.example.nalssi.presentation.widget.MyOptionBottomSheet
import com.example.nalssi.presentation.widget.MyWeatherCard
import com.example.ui.theme.AppTypography
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenCallback: HomeScreenCallback,
) {
    val viewModel: HomeViewModel = koinViewModel()
    val data = viewModel.listWeather.collectAsState().value

    val isOpenBottomSheet = remember { mutableStateOf(false) }
    val query = remember { mutableStateOf("") }
    val refreshState = rememberPullToRefreshState()

    val forceRefresh = {
        viewModel.fetchAllWeather(forceFetch = true)
    }

    Scaffold (
        topBar = { HomeTopBar(onClick = { isOpenBottomSheet.value = true }) },
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center,
            isRefreshing = data is DataState.Loading,
            onRefresh = forceRefresh,
            state = refreshState,
        ) {
            if (isOpenBottomSheet.value) {
                HomeModalBottomSheet(isOpenState = isOpenBottomSheet, homeScreenCallback = homeScreenCallback)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = query.value,
                    onValueChange = { newQuery -> query.value = newQuery; if(newQuery.isBlank()) viewModel.fetchAllWeather()},
                    singleLine = true,
                    label = { Text("Search Weather") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {viewModel.searchWeather(query.value)}),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).clip(
                        RoundedCornerShape(16.dp)
                    ),
                )

                when (data) {
                    is DataState.Error -> MyCenter { Text(data.toString()) }
                    is DataState.Loading -> MyCenter { Text(data.toString()) }
                    is DataState.Success, is DataState.Cached -> {
                        val data = when (data) {
                            is DataState.Success -> data.data
                            is DataState.Cached -> data.data
                            else -> emptyList()
                        }
                        if (data.isEmpty()) {
                            MyCenter { Text("Empty") }
                        }
                        else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeModalBottomSheet(isOpenState: MutableState<Boolean>, homeScreenCallback: HomeScreenCallback) {
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
                modifier = Modifier.clickable { onClick() }.padding(8.dp)
            )
        }
    )
}