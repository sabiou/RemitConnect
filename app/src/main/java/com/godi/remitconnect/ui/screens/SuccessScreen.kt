package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ButtonPrimary
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun SuccessScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.success_image),
                    contentDescription = "Success transaction illustration"
                )
                Text(
                    text = "Your money is on the way. Get excited! ",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                ButtonPrimary(
                    text = "Got it !",
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                    },
                    isEnabled = true,
                )
            }
        },
        containerColor = Color(0xFF08A075)
    )
}

@Composable
@Preview
fun SuccessScreenPreview() {
    RemitConnectTheme {
        //SuccessScreen()
    }
}