package com.godi.remitconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hey, John Doe", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0xFFF2F3F4),
                    shape = RoundedCornerShape(12.dp) // Adjust the corner radius as needed
                ),
            contentAlignment = Alignment.Center) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.notification_icon),
                contentDescription = "top up wallet",
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopSection() {
    RemitConnectTheme {
        TopSection()
    }
}