package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ActionSection
import com.godi.remitconnect.components.NoTransactionsState
import com.godi.remitconnect.components.TopCard
import com.godi.remitconnect.components.TransactionItemCard
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.CustomTheme
import com.godi.remitconnect.utils.formatCurrencyToUsd
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel

/**
 * Composable function for the home screen of the app.
 *
 * @param viewModel The view model for the home screen.
 */
@Composable
fun HomeScreen(
    viewModel: SendingMoneyViewModel = hiltViewModel()
) {
    val transactions = viewModel.getAllTransactions().collectAsState(emptyList())
    val account = viewModel.account.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(account.value?.name ?: "User")
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopCard(balance = formatCurrencyToUsd(account.value?.amount ?: 0.0)) {}
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                    ActionSection()
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                    TransactionsList(
                        transactions = transactions.value
                    )
                }
            }
        },
    )
}

/**
 * Composable function for displaying a list of transactions.
 *
 * @param transactions The list of transactions to display.
 */
@Composable
fun TransactionsList(
    transactions: List<TransactionEntity>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.transactions),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = CustomTheme.colors.midnightBlue,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (transactions.isEmpty()) {
            item {
                NoTransactionsState()
            }
        } else {
            items(transactions) {
                TransactionItemCard(
                    transaction = it
                )
                Divider(
                    color = CustomTheme.colors.silverGrey, thickness = 1.dp
                )
            }
        }
    }
}


/**
 * Composable function for displaying the top app bar in the home screen.
 *
 * @param accountOwner The name of the account owner to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    accountOwner: String
) {
    TopAppBar(
        modifier = Modifier
            .padding(start = 12.dp, end = 24.dp, top = 32.dp, bottom = 32.dp)
            .fillMaxWidth()
            .height(40.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello, $accountOwner",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600,
                    color = CustomTheme.colors.midnightBlue
                )
                Box(
                    modifier = Modifier
                        .clickable { }
                        .size(40.dp)
                        .fillMaxWidth()
                        .background(
                            color = CustomTheme.colors.silverGrey,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.notification_icon),
                        contentDescription = "notifications bell",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            Color.White
        )
    )
}