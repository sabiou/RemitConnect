package com.godi.remitconnect.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.ui.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    hasTitle: Boolean,
    @StringRes titleStringId: Int,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(40.dp),
        title = {
            if (hasTitle)
                Text(
                    text = stringResource(id = titleStringId),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600,
                    color = CustomTheme.colors.midnightBlue
                )
        },
        colors = topAppBarColors(
            Color.White
        )
    )
}