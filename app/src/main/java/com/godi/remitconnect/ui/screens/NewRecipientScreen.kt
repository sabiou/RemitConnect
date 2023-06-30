package com.godi.remitconnect.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ButtonPrimary
import com.godi.remitconnect.data.model.RecipientDetails
import com.godi.remitconnect.ui.countrypicker.CountryPricker
import com.godi.remitconnect.ui.countrypicker.getCountriesList
import com.godi.remitconnect.ui.theme.RemitConnectTheme

@Composable
fun NewRecipientScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var selectedCountryCode by remember { mutableStateOf(getCountriesList().first().countryPhoneCode) }

    Column(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxHeight()
    ) {
        Text(
            text = "Country",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            lineHeight = 21.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CountryPricker(
            pickedCountry = { country ->
                selectedCountryCode = country.countryPhoneCode
                Log.i("COUNTRY", selectedCountryCode)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ChooseContactButton()
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Divider(
                modifier = modifier.weight(1f)
            )
            Text(
                text = "OR ADD MANUALLY",
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 18.sp,
                color = Color(0xFF7F8895)
            )
            Divider(
                modifier = modifier.weight(1f),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Phone number",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            lineHeight = 24.sp,
            color = Color(0xFF00122C)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC3CA),
                unfocusedBorderColor = Color(0xFFBFC3CA),
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "First Name",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            lineHeight = 24.sp,
            color = Color(0xFF00122C)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = firstName,
            onValueChange = {
                firstName = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC3CA),
                unfocusedBorderColor = Color(0xFFBFC3CA),
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Last Name",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            lineHeight = 24.sp,
            color = Color(0xFF00122C)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = lastName,
            onValueChange = {
                lastName = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBFC3CA),
                unfocusedBorderColor = Color(0xFFBFC3CA),
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        ButtonPrimary(
            text = "Continue",
            ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            ),
            onClick = {
                val recipientDetails = RecipientDetails(
                    selectedCountryCode = selectedCountryCode,
                    phoneNumber = phoneNumber,
                    firstName = firstName,
                    lastName = lastName
                )
                navController
                    .navigate(
                        "mobileWallet/${recipientDetails.selectedCountryCode}/${recipientDetails.phoneNumber}/${recipientDetails.firstName}/${recipientDetails.lastName}"
                    )
            },
            isEnabled = true
        )
    }
}

@Composable
fun ChooseContactButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(327.dp, 48.dp)
            .border(
                1.dp, color = Color(0xFFC8EAE1),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            Color(0xFFEDF8F5)
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.choose_contact),
                contentDescription = "choose contact button"
            )
            Text(
                text = "Choose a contact",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
@Preview
fun NewRecipientScreenPreview() {
    RemitConnectTheme {
        //NewRecipientScreen()
    }
}