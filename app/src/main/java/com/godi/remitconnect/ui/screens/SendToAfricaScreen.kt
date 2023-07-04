package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.SendingOption
import com.godi.remitconnect.components.SendingOptionsCard
import com.godi.remitconnect.ui.theme.CustomTheme

@Composable
fun SendToAfricaScreen(
    navController: NavController
) {
    val mobileWallet = SendingOption(
        "Mobile wallets",
        ImageVector.vectorResource(R.drawable.arrow_square_right)
    )
    val bankTransfer = SendingOption(
        "Bank transfer",
        ImageVector.vectorResource(R.drawable.arrow_square_right)
    )
    Scaffold(
        topBar = {
            BackButtonTopBar(
                onClick = {
                    navController.navigateUp()
                }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = stringResource(R.string.send_to_africa),
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
                }
            }
        },
        containerColor = Color.White
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopBar(onClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .padding(end = 12.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxWidth()
            .height(40.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clickable { onClick() }
                        .size(40.dp)
                        .fillMaxWidth()
                        .background(
                            color = CustomTheme.colors.silverGrey,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                        contentDescription = stringResource(R.string.close_desc),
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            Color.White
        )
    )
}