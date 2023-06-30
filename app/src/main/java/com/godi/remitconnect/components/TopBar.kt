package com.godi.remitconnect.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        title = {  },
        colors = topAppBarColors(
            Color.White
        )
    )
}

@Composable
@Preview
fun PreviewTopBar() {
    RemitConnectTheme {
        TopBar()
    }
}