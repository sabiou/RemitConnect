package com.godi.remitconnect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R

@Composable
fun NoTransactionsState() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_transactions_yet),
            fontWeight = FontWeight.Thin,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}