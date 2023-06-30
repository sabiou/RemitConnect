package com.godi.remitconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .size(375.dp, 77.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.picture_1),
                contentDescription = "Contact picture",
                modifier = modifier.size(40.dp)
            )
            Column {
                Text(
                    "Ralph Edwards",
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    "+229 98 767 289",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
            contentDescription = "Choose",
        )
    }
}

@Preview
@Composable
fun PreviewContactListItem() {
    RemitConnectTheme {
        ContactListItem()
    }
}