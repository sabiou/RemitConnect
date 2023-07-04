package com.godi.remitconnect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun ActionSection(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ActionCard(
                CardOption(
                    stringResource(R.string.top_up_balance),
                    R.drawable.empty_wallet_add
                ),
            ) {}
           ActionCard(
               option = CardOption(
                   stringResource(R.string.withdrawn_money),
                   R.drawable.wallet_minus
               )
           ) {}
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ActionCard(
                option = CardOption(
                    stringResource(R.string.get_iban),
                    R.drawable.wallet_minus
                )
            ) {}
            ActionCard(
                option = CardOption(
                    stringResource(R.string.view_analytics),
                    R.drawable.wallet_minus
                )
            ) {}
        }
    }
}

@Preview
@Composable
fun PreviewActionSection() {
    RemitConnectTheme {
        ActionSection()
    }
}