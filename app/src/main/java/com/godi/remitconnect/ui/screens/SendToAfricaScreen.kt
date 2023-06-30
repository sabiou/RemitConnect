package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.SendingOption
import com.godi.remitconnect.components.SendingOptionsCard
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun SendToAfricaScreen(
    navController: NavController
) {

    val mobileWallet = SendingOption(
        "Mobile wallets", ImageVector.vectorResource(R.drawable.arrow_square_right)
    )
    val bankTransfer = SendingOption(
        "Bank transfer", ImageVector.vectorResource(R.drawable.arrow_square_right)
    )
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Send to Africa",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    SendingOptionsCard(
                        mobileWallet,
                        onClick = { navController.navigate("selectRecipient") }
                    )
                    Divider(thickness = 0.5.dp)
                    SendingOptionsCard(bankTransfer, {})
                    Divider(thickness = 0.5.dp)
                }
            }
        },
        containerColor = Color.White
    )
}

@Composable
@Preview
fun SendMoneyToAfricaPreview() {
    RemitConnectTheme {
        //SendToAfricaScreen()
    }
}