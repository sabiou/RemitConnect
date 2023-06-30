package com.godi.remitconnect.ui.countrypicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun CountryPricker(
    modifier: Modifier = Modifier,
    isOnlyFlagShow: Boolean = false,
    defaultSelectedCountry: CountryCode = getCountriesList().first(),
    pickedCountry: (CountryCode) -> Unit,
    dialogRounded: Int = 12
) {
    val countryList: List<CountryCode> = getCountriesList()
    var isPickCountry by remember { mutableStateOf(defaultSelectedCountry) }
    var isOpenDialog by remember { mutableStateOf(false) }

    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFBFC3CA)
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { isOpenDialog = true }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .size(327.dp, 56.dp)
                .padding(start = 16.dp, end = 12.dp)
                .clickable { isOpenDialog = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = getFlags(
                            isPickCountry.countryCode
                        ),
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .size(24.dp),
                )
                if (!isOnlyFlagShow) {
                    Text(
                        isPickCountry.countryName,
                        Modifier.padding(8.dp),
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W500,
                        lineHeight = 24.sp
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    isPickCountry.countryPhoneCode,
                    color = Color(0xFF7F8895),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = modifier.padding(horizontal = 13.dp)
                )
            }
        }
    }

    // choose country dialog
    if (isOpenDialog) {
        Dialog(
            onDismissRequest = { isOpenDialog = false },
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                elevation = CardDefaults.cardElevation(),
                shape = RoundedCornerShape(dialogRounded.dp)
            ) {
                Column {
                    LazyColumn {
                        items(
                            countryList
                        ) { countryItem ->
                            Row(
                                Modifier
                                    .padding(
                                        horizontal = 18.dp,
                                        vertical = 18.dp
                                    )
                                    .clickable {
                                        pickedCountry(countryItem)
                                        isPickCountry = countryItem
                                        isOpenDialog = false
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = getFlags(
                                            countryItem.countryCode
                                        )
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .size(24.dp),
                                )
                                Text(
                                    countryItem.countryName,
                                    Modifier.padding(horizontal = 18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CountryPickerPreview() {
    RemitConnectTheme {
        CountryPricker(pickedCountry = {})
    }
}