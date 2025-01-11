package com.example.nalssi.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nalssi.core.theme.AppTypography

@Composable
fun MyInfoCard(
    icon: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(240.dp)
            .height(80.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Card Icon",
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = title,
                    style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = subtitle,
                    style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}