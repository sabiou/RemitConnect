package com.godi.remitconnect.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun ButtonPrimary(
    text: String,
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
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 24.sp,
        )
    }
}

@Composable
@Preview
fun PreviewButtonPrimary() {
    RemitConnectTheme {
        ButtonPrimary(
            "Continue",
            ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            ),
            onClick = {},
            isEnabled = true
        )
    }
}