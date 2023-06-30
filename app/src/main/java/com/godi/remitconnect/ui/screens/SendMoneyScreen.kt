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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.godi.remitconnect.R
import com.godi.remitconnect.components.SendingOption
import com.godi.remitconnect.components.SendingOptionsCard
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.data.model.RecipientDetails
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun SendMoneyScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val monecoBalance =
        SendingOption("To Moneco balance", ImageVector.vectorResource(R.drawable.moneco_balance))
    val bankTransfer =
        SendingOption("Bank transfer", ImageVector.vectorResource(R.drawable.bank_transfer))
    val sendToAfrica =
        SendingOption("Send to Africa", ImageVector.vectorResource(R.drawable.send_to_africa))

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Send money",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(
                        thickness = 0.5.dp
                    )
                    SendingOptionsCard(
                        monecoBalance,
                        onClick = {}
                    )
                    Divider(thickness = 0.5.dp)
                    SendingOptionsCard(
                        bankTransfer,
                        onClick = {}
                    )
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

@Composable
fun SendMoneyScreenGraph(navController: NavHostController) {
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
        ) { backStackEntry ->
            val selectedCountryCode = backStackEntry.arguments?.getString("selectedCountryCode")
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
            val firstName = backStackEntry.arguments?.getString("firstName")
            val lastName = backStackEntry.arguments?.getString("lastName")
            val mobileWallet = backStackEntry.arguments?.getString("mobileWallet")
            val recipientDetails = RecipientDetails(
                selectedCountryCode,
                phoneNumber,
                firstName,
                lastName,
                mobileWallet
            )
            SendingMoneyScreen(recipientDetails = recipientDetails, navController = nestedNavController)
        }
        composable("success") {
            SuccessScreen(nestedNavController)
        }
    }
}

@Composable
@Preview
fun SendMoneyScreenPreview() {
    RemitConnectTheme {
        //SendMoneyScreen()
    }
}