package com.example.nalssi.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nalssi.R
import com.example.nalssi.presentation.callback.ProfileScreenCallback
import com.example.nalssi.core.theme.AppTypography

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileScreenCallback: ProfileScreenCallback,
) {
    Scaffold(
        topBar = { ProfileTopBar(onClick = { profileScreenCallback.onBackClicked() }) },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = "file:///android_asset/1701137696391.png",
                    contentDescription = "Profile Picture",
                    placeholder = painterResource(id = R.drawable.ic_person),
                    error = painterResource(id = R.drawable.ic_person),
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = "Kadek Faraday",
                    style = AppTypography.titleMedium
                )
                Text(
                    text = "kdkfrdy@gmail.com",
                    style = AppTypography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text("Profile") },
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                )
            }
        })
}