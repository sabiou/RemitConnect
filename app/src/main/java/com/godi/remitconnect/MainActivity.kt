package com.godi.remitconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.godi.remitconnect.navigation.NavigationItem
import com.godi.remitconnect.navigation.RemitBottomBar
import com.godi.remitconnect.ui.screens.CardScreen
import com.godi.remitconnect.ui.screens.HomeScreen
import com.godi.remitconnect.ui.screens.SendMoneyScreenGraph
import com.godi.remitconnect.ui.screens.SettingScreen
import com.godi.remitconnect.ui.screens.TontineScreen
import com.godi.remitconnect.ui.theme.RemitConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemitConnectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != NavigationItem.Send.route) {
                RemitBottomBar(navController = navController)
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                RemitConnectNavGraph(navController = navController)
            }
        },
    )
}

/**
 * Composable function that defines the navigation graph for RemitConnect app.
 *
 * @param navController The NavHostController used for navigation.
 */
@Composable
fun RemitConnectNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Cards.route) {
            CardScreen()
        }
        composable(NavigationItem.Send.route) {
            SendMoneyScreenGraph()
        }
        composable(NavigationItem.Tontines.route) {
            TontineScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen()
        }
    }
}