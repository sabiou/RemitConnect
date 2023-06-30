package com.godi.remitconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R
import com.godi.remitconnect.data.db.RecipientEntity
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.RemitConnectTheme

data class Transaction(val recipientName: String, val amount: String)

@Composable
fun TransactionItemCard(
    modifier: Modifier = Modifier,
    transaction: TransactionEntity,
) {
    Row(
        modifier = modifier
            .size(327.dp, 74.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0xFFEAF6FC),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_up_right),
                contentDescription = "Sent",
                tint = Color(0xFF1B98E0)
            )
        }
        Column {
            Text("Sent to", fontWeight = FontWeight.Thin, fontSize = 14.sp)
            Text("${transaction.recipient.firstName} ${transaction.recipient.lastName}", fontWeight = FontWeight.Medium)
        }
        Text(text = transaction.amount, fontWeight = FontWeight.Medium)
    }
}

@Preview
@Composable
fun PreviewTransactionItemCard() {
    val recipient = RecipientEntity(country = "+221", phoneNumber = "90898998", mobile_wallet = "Wave", firstName = "Farouk", lastName = "Sabiou")
    val transactionPrev = TransactionEntity(amount = "$300", recipient = recipient)
    RemitConnectTheme {
        TransactionItemCard(transaction = transactionPrev)
    }
}