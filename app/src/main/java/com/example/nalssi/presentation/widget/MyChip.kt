package com.example.nalssi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.onPrimaryContainerDark
import com.example.compose.onPrimaryContainerLight
import com.example.ui.theme.AppTypography

@Composable
fun MyChip(text: String, modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 4.dp, horizontal = 8.dp),

        ) {
        Text(
            text = text,
            style = AppTypography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer, textAlign = TextAlign.Center),
        )
    }
}