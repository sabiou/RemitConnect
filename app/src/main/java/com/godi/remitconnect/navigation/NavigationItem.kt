package com.godi.remitconnect.navigation

import com.godi.remitconnect.R

/**
 * Sealed class representing a navigation item.
 *
 * @property route The route associated with the navigation item.
 * @property icon The icon resource ID representing the navigation item's icon.
 * @property title The title of the navigation item.
 */

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Cards : NavigationItem("cards", R.drawable.cards, "Cards")
    object Send : NavigationItem("send", R.drawable.send, "Send")
    object Tontines : NavigationItem("tontines", R.drawable.coin, "Tontines")
    object Settings : NavigationItem("Settings", R.drawable.settings, "Settings")
}