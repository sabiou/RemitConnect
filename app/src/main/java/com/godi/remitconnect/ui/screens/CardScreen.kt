package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ComingSoon
import com.godi.remitconnect.components.TopBar

@Composable
fun CardScreen(
) {
    Scaffold(
        topBar = {
            TopBar(
                hasTitle = true,
                R.string.Cards
            )
        },
        content = {
            Box(
                Modifier.padding(it)
            ) {
               ComingSoon()
            }
        }
    )
}