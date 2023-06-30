package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.components.CustomTabs
import com.godi.remitconnect.components.RemitSearchBar
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun SelectRecipientScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Who are you sending to?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    CustomTabs(navController)
                    Spacer(modifier = Modifier.height(24.dp))
                    RemitSearchBar()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        },
        containerColor = Color.White
    )
}

@Composable
@Preview
fun SelectRecipientScreenPreview() {
    RemitConnectTheme {
        //SelectRecipientScreen()
    }
}