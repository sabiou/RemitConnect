package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ContactListItem
import com.godi.remitconnect.ui.theme.CustomTheme
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel

@Composable
fun PreviousRecipientScreen(
    navController: NavController
) {
    val searchQuery = remember { mutableStateOf("") }
    val viewModel: SendingMoneyViewModel = hiltViewModel()
    val filteredTransactions =
        viewModel.searchTransactionsByName(searchQuery.value).collectAsState(emptyList())

    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        CustomSearchBar(searchQuery.value) { query ->
            searchQuery.value = query
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.contacts_on_your_phone),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 21.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            if (filteredTransactions.value.isEmpty() && searchQuery.value.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.no_contact_saved),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(filteredTransactions.value) { contact ->
                    ContactListItem(contact) {
                        navController
                            .navigate(
                                "mobileWallet/${contact.country}/${contact.phoneNumber}/${contact.firstName}/${contact.lastName}"
                            )
                    }
                    Divider(
                        color = CustomTheme.colors.silverGrey, thickness = 1.dp
                    )
                }
            }
            if (filteredTransactions.value.isEmpty() && searchQuery.value.isNotEmpty()) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_match_state),
                            contentDescription = stringResource(R.string.no_results),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .wrapContentHeight(),
                            contentScale = ContentScale.Fit
                        )
                        Text(text = stringResource(R.string.no_matching_results_found))
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .size(327.dp, 50.dp),
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(12.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                fontSize = 14.sp,
                color = CustomTheme.colors.duskGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = stringResource(R.string.search_icon_desc)
            )
        },
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = CustomTheme.colors.silverGrey,
            focusedContainerColor = CustomTheme.colors.silverGrey,
            unfocusedContainerColor = CustomTheme.colors.silverGrey,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
    )
}