package com.godi.remitconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.CustomTheme

@Composable
fun ContactListItem(
    transaction: TransactionEntity,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .height(77.dp)
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth()
                .background(
                    color = CustomTheme.colors.babyBlue,
                    shape = RoundedCornerShape(12.dp)
                ),
            painter = painterResource(id = R.drawable.picture_1),
            contentDescription = stringResource(R.string.contact_picture_desc),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = "${transaction.firstName} ${transaction.lastName}",
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
            Text(
                text = "${transaction.country} ${transaction.phoneNumber}",
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
            contentDescription = stringResource(R.string.select_desc),
        )
    }
}