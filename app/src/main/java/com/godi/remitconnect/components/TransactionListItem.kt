package com.godi.remitconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.CustomTheme
import com.godi.remitconnect.utils.formatCurrency

@Composable
fun TransactionItemCard(transaction: TransactionEntity) {
    Row(
        modifier = Modifier
            .height(74.dp)
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth()
                .background(
                    color = CustomTheme.colors.babyBlue,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_up_right),
                contentDescription = stringResource(R.string.sent),
                tint = CustomTheme.colors.ceruleanBlue
            )
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Column(
            Modifier.weight(1f)
        ) {
            Text(stringResource(R.string.sent_to), fontWeight = FontWeight.Thin, fontSize = 14.sp)
            Text("${transaction.firstName} ${transaction.lastName}", fontWeight = FontWeight.Medium)
        }
        val formattedAmount = formatCurrency(transaction.amount!!)
        Text(text = formattedAmount, fontWeight = FontWeight.Medium)
    }
}