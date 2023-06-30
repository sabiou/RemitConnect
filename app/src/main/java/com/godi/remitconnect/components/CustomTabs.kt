package com.godi.remitconnect.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.ui.screens.NewRecipientScreen
import com.godi.remitconnect.ui.screens.PreviousRecipientScreen
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun CustomTabs(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val list = listOf("Previous recipients", "New recipient")

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = {},
        modifier = modifier
            .fillMaxWidth()
            .size(327.dp, 48.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        list.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                else Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ),
                selected = selected,
                onClick = { selectedIndex = index },
                text = {
                    Text(
                        text = text,
                        color = if (selected) Color.White else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                    )
                }
            )
        }
    }
    // Content based on selected tab index
    when (selectedIndex) {
        0 -> PreviousRecipientScreen()
        1 -> NewRecipientScreen(navController)
    }
}

@Composable
@Preview
fun PreviewCustomTabs() {
    RemitConnectTheme {
        //CustomTabs()
    }
}