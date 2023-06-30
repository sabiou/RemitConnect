package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ButtonPrimary
import com.godi.remitconnect.components.TopBar
import com.godi.remitconnect.data.db.RecipientEntity
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.data.model.RecipientDetails
import com.godi.remitconnect.ui.theme.RemitConnectTheme
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun SendingMoneyScreen(
    modifier: Modifier = Modifier,
    recipientDetails: RecipientDetails,
    navController: NavController,
    viewModel: SendingMoneyViewModel = hiltViewModel()
) {
    val conversionRate = 571.00
    var amount by rememberSaveable { mutableStateOf("") }
    var isTextFieldEmpty by rememberSaveable { mutableStateOf(true) }

    val recipientAmount = rememberSaveable(amount) {
        amount.toFloatOrNull()?.times(conversionRate) ?: 0.0f
    }
    var confirmBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    if (confirmBottomSheetVisible) {
        ConfirmationBottomSheet(
            amount,
            recipientDetails.selectedCountryCode!!,
            recipientDetails.phoneNumber!!,
            recipientDetails.firstName!!,
            recipientDetails.lastName!!,
            recipientDetails.mobileWallet!!,
            navController
        ) {
            confirmBottomSheetVisible = false
            val recipient = RecipientEntity(
                firstName = recipientDetails.firstName,
                lastName = recipientDetails.lastName,
                country = recipientDetails.selectedCountryCode,
                phoneNumber = recipientDetails.phoneNumber,
                mobile_wallet = recipientDetails.mobileWallet
            )
            viewModel.saveTransaction(
                TransactionEntity(recipient = recipient, amount = amount)
            )
        }
    }
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Send money",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "How much are you sending?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 36.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .size(327.dp, 97.dp)
                            .border(
                                1.dp,
                                color = if (isTextFieldEmpty) Color(0xFFF2F3F4) else Color(
                                    0xFF08A075
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                TextField(
                                    value = amount,
                                    onValueChange = {
                                        amount = it
                                        isTextFieldEmpty = it.isEmpty()
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W600,
                                        color = Color(0xFF00122C),
                                        fontFamily = FontFamily(
                                            Font(R.font.outfit_bold)
                                        )
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "00",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.W600,
                                                color = Color(0xFF00122C),
                                                fontFamily = FontFamily(
                                                    Font(R.font.outfit_bold)
                                                )
                                            ),
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    )
                                )
                                Text(
                                    text = "EUR",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color(0xFF7F8895)
                                )
                            }
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .size(327.dp, 40.dp)
                                    .background(
                                        if (!isTextFieldEmpty) MaterialTheme.colorScheme.surfaceVariant
                                        else Color(0xFFF2F3F4)
                                    )
                            ) {
                                Row(
                                    modifier = modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Your current balance is 230 EUR",
                                        fontSize = 12.sp,
                                        color = if (!isTextFieldEmpty) MaterialTheme.colorScheme.primary else Color(
                                            0xFF00122C
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                        Text(
                            text = "Yearly free remittances",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 36.sp,
                        )
                        Text(
                            text = "Remittances are free with Moneco until you reach your limit, which resets every year.",
                            fontSize = 14.sp,
                            color = Color(0xFF7F8895)
                        )
                        Text(
                            text = "Check number of free remittance remaining",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1B98E0)
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = "Fees breakdown",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 36.sp,
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
                            modifier = Modifier.padding(bottom = 24.dp)
                        ) {
                            val fees = listOf(
                                Fee("Moneco fees", "0.0 EUR"),
                                Fee("Transfer fees", "0.0 EUR"),
                                Fee("Conversion rate", "571.00 XOF"),
                                Fee("You'll spend in total", "0.0 EUR")
                            )
                            fees.forEach { fee ->
                                FeesRow(fee = fee)
                            }
                            DashedLine()
                            RecipientAmountRow(
                                text = "Recipient gets",
                                calculatedValue = recipientAmount.toString()
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            ButtonPrimary(
                                text = "Send",
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                onClick = { confirmBottomSheetVisible = true },
                                isEnabled = !isTextFieldEmpty
                            )
                        }
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationBottomSheet(
    amount: String,
    selectedCountryCode: String,
    phoneNumber: String,
    firstName: String,
    lastName: String,
    optionName: String,
    navController: NavController,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                text = "Confirm transfer",
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF00122C)
            )
            ConfirmationRow(description = "You're sending", text = "$amount XOF")
            ConfirmationRow(description = "To", text = "$firstName $lastName")
            ConfirmationRow(
                description = "Via",
                text = "$optionName : $selectedCountryCode $phoneNumber"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            navController.navigate("success") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(311.dp, 56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}

@Composable
fun DashedLine(
    modifier: Modifier = Modifier
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    Canvas(
        modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = Color(0xFF7F8895),
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

data class Fee(val name: String, val amount: String)

@Composable
fun FeesRow(
    fee: Fee,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = fee.name,
            fontSize = 14.sp,
            color = Color(0xFF7F8895)
        )
        Text(
            text = fee.amount,
            fontSize = 14.sp,
            color = Color(0xFF00122C)
        )
    }
}

@Composable
fun RecipientAmountRow(
    text: String,
    calculatedValue: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color(0xFF7F8895)
        )
        Text(
            text = "$calculatedValue XOF",
            fontSize = 18.sp,
            color = Color(0xFF00122C),
            fontWeight = FontWeight.W600,
        )
    }
}

@Composable
fun ConfirmationRow(
    description: String,
    text: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color(0xFF7F8895)
        )
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF00122C)
        )
    }
}

@Composable
@Preview
fun PreviewSendingMoneyScreen() {
    RemitConnectTheme {
        //SendingMoneyScreen(navController = rememberNavController())
    }
}