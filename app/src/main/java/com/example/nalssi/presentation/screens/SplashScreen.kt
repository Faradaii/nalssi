package com.example.nalssi.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nalssi.R
import com.example.nalssi.presentation.callback.SplashScreenCallback
import com.example.nalssi.core.theme.AppTypography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    splashScreenCallback: SplashScreenCallback
) {
    val alphaAnimation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
        )
        delay(3000)
        splashScreenCallback.onRedirected()
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(innerPadding),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nalssi),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(300.dp)
                        .alpha(alphaAnimation.value)
                )
                Text(
                    text = "Nalssi",
                    style = AppTypography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.alpha(alphaAnimation.value)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "From",
                    style = AppTypography.titleSmall,
                    modifier = Modifier.alpha(alphaAnimation.value)
                )
                Text(
                    text = "Faradaii",
                    style = AppTypography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.alpha(alphaAnimation.value)
                )
            }
        }
    }
}