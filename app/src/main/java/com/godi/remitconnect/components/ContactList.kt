package com.godi.remitconnect.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun ContactList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(20) {
            ContactListItem()
        }
    }
}

@Preview
@Composable
fun ContactListPreview() {
    RemitConnectTheme {
        ContactList()
    }
}