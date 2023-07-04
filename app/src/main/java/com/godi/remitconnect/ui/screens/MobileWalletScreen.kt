package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ButtonPrimary
import com.godi.remitconnect.data.model.RecipientDetails
import com.godi.remitconnect.ui.theme.CustomTheme

@Composable
fun MobileWalletScreen(
    navController: NavController,
    recipientDetails: RecipientDetails,
    modifier: Modifier = Modifier
) {
    val wave = MobileWalletOption(
        "Wave",
        R.drawable.wave_logo
    )
    val mtn = MobileWalletOption(
        "MTN Mobile Money",
        R.drawable.mtn_logo
    )
    val om = MobileWalletOption(
        "Orange Money",
        R.drawable.orange_money_logo
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
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .padding(padding)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
            ) {
                var selectedOption by remember { mutableStateOf<MobileWalletOption?>(null) }
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.choose_a_mobile_wallet),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                    )
                    MobileWalletOptionCard(
                        option = wave,
                        selected = selectedOption == wave
                    ) {
                        selectedOption = wave
                    }
                    MobileWalletOptionCard(
                        option = mtn,
                        selected = selectedOption == mtn
                    ) {
                        selectedOption = mtn
                    }
                    MobileWalletOptionCard(
                        option = om,
                        selected = selectedOption == om
                    ) {
                        selectedOption = om
                    }
                }
                ButtonPrimary(
                    textResId = R.string.continue_txt,
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        navController.navigate(
                            "finalizeTransaction/${recipientDetails.selectedCountryCode}/${recipientDetails.phoneNumber}/" +
                                    "${recipientDetails.firstName}/" +
                                    "${recipientDetails.lastName}/" +
                                    "${selectedOption?.mobileWallet}"
                        )
                    },
                    isEnabled = selectedOption != null
                )
            }
        },
        containerColor = Color.White
    )
}

data class MobileWalletOption(val mobileWallet: String, val optionLogoResource: Int)

@Composable
fun MobileWalletOptionCard(
    option: MobileWalletOption,
    selected: Boolean,
    onSelected: () -> Unit
) {
    val borderColor = if (selected) MaterialTheme.colorScheme.primary else CustomTheme.colors.silverGray
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) CustomTheme.colors.paleMint else Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(327.dp, 72.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelected() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(
                    id = option.optionLogoResource
                ),
                contentDescription = option.mobileWallet
            )
            Text(
                text = option.mobileWallet,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
            )
        }
    }
}