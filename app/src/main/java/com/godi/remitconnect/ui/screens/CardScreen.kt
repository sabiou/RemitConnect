package com.godi.remitconnect.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.components.TransactionItemCard
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel
import kotlinx.coroutines.flow.emptyFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardScreen(
    viewModel: SendingMoneyViewModel = hiltViewModel()
) {
    val transactions = viewModel.getAllTransactions().collectAsState(initial = emptyList())

    Log.i("transactions", transactions.value.toString())

    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
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
    )
}