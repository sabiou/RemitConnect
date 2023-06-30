package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun PreviousRecipientScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {
        Text(
            text = "Contacts on your phone",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            lineHeight = 21.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            thickness = 0.5.dp
        )
    }
}

@Composable
@Preview
fun PreviousRecipientPreview() {
    RemitConnectTheme {
        PreviousRecipientScreen()
    }
}