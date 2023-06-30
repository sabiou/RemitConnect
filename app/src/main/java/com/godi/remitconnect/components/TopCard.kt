package com.godi.remitconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun TopCard(
    modifier: Modifier = Modifier, balance:
    String, onClick: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        listOf(
            Color(0xFF00A85A), Color(0xFF055C00)
        ),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(brush = gradientBrush)
            .clickable {
                onClick()
            }, horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(
                    top = 36.dp, bottom = 36.dp
                )
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Your balance",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = balance,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "US Dollars",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(56.dp)
                .offset(y = 32.dp)
                .background(
                    color = Color(1.0f, 1.0f, 1.0f, 0.2f),
                    shape = RoundedCornerShape(16.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.moneys),
                contentDescription = "balance",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun TopCardPreview() {
    RemitConnectTheme {
        TopCard(
            balance = "320,000",
        ) {
            // click action
        }
    }
}