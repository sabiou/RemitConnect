package com.godi.remitconnect.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.remitconnect.R
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemitSearchBar(
    modifier: Modifier = Modifier,
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .size(327.dp, 40.dp),
        query = text,
        onQueryChange = {
            text = it
        },
        shape = RoundedCornerShape(12.dp),
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = "Search icon"
            )
        }
    ) {
    }
}

@Composable
@Preview
fun PreviewRemitSearchBar() {
    RemitConnectTheme {
        RemitSearchBar()
    }
}