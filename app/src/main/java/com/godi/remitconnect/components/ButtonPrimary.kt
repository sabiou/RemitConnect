package com.godi.remitconnect.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonPrimary(
    @StringRes textResId: Int,
    colors: ButtonColors,
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(311.dp, 56.dp),
        colors = colors,
        enabled = isEnabled
    ) {
        Text(
            text = stringResource(id = textResId),
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 24.sp,
        )
    }
}