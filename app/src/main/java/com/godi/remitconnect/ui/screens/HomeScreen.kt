package com.godi.remitconnect.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godi.remitconnect.components.ActionSection
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.components.TopCard
import com.godi.remitconnect.components.TransactionItemCard
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.RemitConnectTheme
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel

@Composable
fun HomeScreen(
    viewModel: SendingMoneyViewModel = hiltViewModel()
) {
    val transactions = viewModel.getAllTransactions().collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopCard(balance = "320,000") {}
                    Spacer(modifier = Modifier.height(24.dp))
                    ActionSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            Text(text = "Transactions")
                        }
                        items(transactions.value) {
                            TransactionItemCard(
                                transaction = it
                            )
                        }
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun TransactionList(
    transactions: List<TransactionEntity>
) {
    LazyColumn {
        items(transactions) {

        }
    }
}

@Composable
@Preview
fun TransactionListPrev() {
    RemitConnectTheme {
//        TransactionList(viewModel = viewModel)
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    RemitConnectTheme {
        HomeScreen()
    }
}