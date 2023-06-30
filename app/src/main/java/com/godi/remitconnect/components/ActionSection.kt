package com.godi.remitconnect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun ActionSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actionsOptions.take(2).forEach { data ->
                ActionCard(data) {}
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            actionsOptions.drop(2).forEach { data ->
                ActionCard(data) {}
            }
        }
    }
}

val actionsOptions = listOf(
    CardOption(
        "Top up balance",
        R.drawable.empty_wallet_add
    ),
    CardOption(
        "Withdrawn money",
        R.drawable.wallet_minus
    ),
    CardOption(
        "GET IBAN",
        R.drawable.wallet_minus
    ),
    CardOption(
        "View analytics",
        R.drawable.wallet_minus
    ),
)

@Preview
@Composable
fun PreviewActionSection() {
    RemitConnectTheme {
        ActionSection()
    }
}