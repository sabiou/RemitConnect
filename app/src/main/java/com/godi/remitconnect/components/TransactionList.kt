package com.godi.remitconnect.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.ui.theme.RemitConnectTheme

//@Composable
//fun TransactionList(modifier: Modifier = Modifier) {
//    val transactions = listOf(
//        Transaction("Ralph Edwards", "$150"),
//        Transaction("Kathryn Murphy", "$50"),
//        Transaction("Arlene McCoy", "$1500"),
//        Transaction("Robert FoFox", "$500"),
//        Transaction("Jacob Jones", "$200"),
//        Transaction("Jerome Bell", "$350"),
//    )
//    LazyColumn(
//        modifier = modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(16.dp))
//            .padding(top = 16.dp, end = 16.dp, start = 16.dp),
//        horizontalAlignment = Alignment.Start
//    ) {
//        items(transactions) { transaction ->
//            TransactionItemCard(
//                transaction = transaction,
//                amount = transaction.amount
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewTransactionList() {
//    RemitConnectTheme {
//        TransactionList()
//    }
//}