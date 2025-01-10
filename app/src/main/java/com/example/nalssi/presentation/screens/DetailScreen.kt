package com.example.nalssi.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nalssi.R
import com.example.nalssi.presentation.callback.DetailScreenCallback

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailScreenCallback: DetailScreenCallback,
    customId: String
) {

    Scaffold(
        topBar = { DetailTopBar(onClick = { detailScreenCallback.onBackClicked() }) },
    ) { _ ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text("Detail") },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        }
    )
}