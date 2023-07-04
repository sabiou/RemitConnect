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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.godi.remitconnect.R
import com.godi.remitconnect.components.SendingOption
import com.godi.remitconnect.components.SendingOptionsCard
import com.godi.remitconnect.data.model.RecipientDetails
import com.godi.remitconnect.ui.theme.CustomTheme

@Composable
fun SendMoneyScreen(
    navController: NavHostController,
) {
    val monecoBalance =
        SendingOption("To Moneco balance", ImageVector.vectorResource(R.drawable.moneco_balance))
    val bankTransfer =
        SendingOption("Bank transfer", ImageVector.vectorResource(R.drawable.bank_transfer))
    val sendToAfrica =
        SendingOption("Send to Africa", ImageVector.vectorResource(R.drawable.send_to_africa))

    Scaffold(
        topBar = {
            CloseButtonTopBar(
                onClick = {
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = stringResource(R.string.send_money),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    SendingOptionsCard(
                        monecoBalance,
                        onClick = {}
                    )
                    Divider(thickness = Dp.Hairline)
                    SendingOptionsCard(
                        bankTransfer,
                        onClick = {}
                    )
                    Divider(thickness = Dp.Hairline)
                    SendingOptionsCard(
                        sendToAfrica,
                        onClick = {
                            navController.navigate("sendToAfrica")
                        }
                    )
                }
            }
        },
        containerColor = Color.White
    )
}

/**
 * Composable function for defining the navigation graph for the send money flow.
 */
@Composable
fun SendMoneyScreenGraph() {
    val nestedNavController = rememberNavController()

    NavHost(navController = nestedNavController, startDestination = "sendMoney") {
        composable("sendMoney") {
            SendMoneyScreen(nestedNavController)
        }
        composable("sendToAfrica") {
            SendToAfricaScreen(nestedNavController)
        }
        composable("mobileWallet/{selectedCountryCode}/{phoneNumber}/{firstName}/{lastName}",
            arguments = listOf(
                navArgument("selectedCountryCode") { type = NavType.StringType },
                navArgument("phoneNumber") { type = NavType.StringType },
                navArgument("firstName") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val selectedCountryCode = backStackEntry.arguments?.getString("selectedCountryCode")
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
            val firstName = backStackEntry.arguments?.getString("firstName")
            val lastName = backStackEntry.arguments?.getString("lastName")
            val recipientDetails = RecipientDetails(
                selectedCountryCode = selectedCountryCode,
                phoneNumber = phoneNumber,
                firstName = firstName,
                lastName = lastName
            )
            MobileWalletScreen(nestedNavController, recipientDetails)
        }
        composable("selectRecipient") {
            SelectRecipientScreen(nestedNavController)
        }
        composable(
            "finalizeTransaction/{selectedCountryCode}/{phoneNumber}/{firstName}/{lastName}/{mobileWallet}",
            arguments = listOf(
                navArgument("selectedCountryCode") { type = NavType.StringType },
                navArgument("phoneNumber") { type = NavType.StringType },
                navArgument("firstName") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType },
                navArgument("mobileWallet") { type = NavType.StringType }
            )
        ) {
            SendingMoneyScreen(
                nestedNavController
            )
        }
        composable("success") {
            SuccessScreen(nestedNavController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseButtonTopBar(
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .padding(end = 12.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxWidth()
            .height(40.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
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
                        imageVector = ImageVector.vectorResource(id = R.drawable.close),
                        contentDescription = "close",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            Color.White
        )
    )
}