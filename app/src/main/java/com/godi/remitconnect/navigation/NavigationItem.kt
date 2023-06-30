package com.godi.remitconnect.navigation

import com.godi.remitconnect.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Cards : NavigationItem("cards", R.drawable.cards, "Cards")
    object Send : NavigationItem("send", R.drawable.send, "Send")
    object Tontines : NavigationItem("tontines", R.drawable.coin, "Tontines")
    object Settings : NavigationItem("Settings", R.drawable.settings, "Settings")
}