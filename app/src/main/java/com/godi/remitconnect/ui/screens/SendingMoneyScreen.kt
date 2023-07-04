package com.godi.remitconnect.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.godi.remitconnect.R
import com.godi.remitconnect.components.ButtonPrimary
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.ui.theme.CustomTheme
import com.godi.remitconnect.utils.formatAmountToXOF
import com.godi.remitconnect.utils.formatCurrency
import com.godi.remitconnect.viewmodel.SendingMoneyViewModel
import kotlinx.coroutines.launch

@Composable
fun SendingMoneyScreen(
    navController: NavController,
) {
    val viewModel: SendingMoneyViewModel = hiltViewModel()
    val backStackEntry = navController.currentBackStackEntry
    val arguments = backStackEntry?.arguments

    val account = viewModel.account.collectAsState()

    val selectedCountryCode = arguments?.getString("selectedCountryCode")
    val phoneNumber = arguments?.getString("phoneNumber")
    val firstName = arguments?.getString("firstName")
    val lastName = arguments?.getString("lastName")
    val mobileWallet = arguments?.getString("mobileWallet")

    val conversionRate = 655.94
    var amount by rememberSaveable { mutableStateOf("") }

    var isTextFieldEmpty by rememberSaveable { mutableStateOf(true) }

    var confirmBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    if (confirmBottomSheetVisible) {
        val transaction = TransactionEntity(
            country = selectedCountryCode,
            phoneNumber = phoneNumber,
            firstName = firstName,
            lastName = lastName,
            mobile_wallet = mobileWallet,
            amount = amount.toDouble()
        )
        ConfirmationBottomSheet(
            transaction,
            navController,
            onConfirm = {
                viewModel.saveTransaction(transaction)
            },
        ) {
            confirmBottomSheetVisible = false
        }
    }
    Scaffold(
        topBar = {
            BackButtonTopBar(
                onClick = {
                    navController.navigateUp()
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = Modifier
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(327.dp, 97.dp)
                            .border(
                                1.dp,
                                color = if (isTextFieldEmpty) CustomTheme.colors.silverGrey else
                                    MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                val maxChar = 5
                                TextField(
                                    value = amount,
                                    onValueChange = {
                                        if (it.length <= maxChar) amount = it
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
                                        color = CustomTheme.colors.midnightBlue,
                                        fontFamily = FontFamily(
                                            Font(R.font.outfit_bold)
                                        )
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "0.0",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.W600,
                                                color = CustomTheme.colors.midnightBlue,
                                                fontFamily = FontFamily(
                                                    Font(R.font.outfit_bold)
                                                )
                                            ),
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    singleLine = true,
                                    maxLines = 1,
                                    keyboardActions = KeyboardActions(
                                        onNext = KeyboardActions.Default.onNext
                                    )
                                )
                                Text(
                                    text = "EUR",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W600,
                                    color = CustomTheme.colors.duskGray
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(327.dp, 40.dp)
                                    .background(
                                        if (!isTextFieldEmpty) MaterialTheme.colorScheme.surfaceVariant
                                        else CustomTheme.colors.silverGrey
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Your current balance is ${formatCurrency(account.value?.amount ?: 0.0)}",
                                        fontSize = 12.sp,
                                        color = if (!isTextFieldEmpty)
                                            MaterialTheme.colorScheme.primary
                                        else CustomTheme.colors.midnightBlue
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
                            text = stringResource(R.string.yearly_free_remittances),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 36.sp,
                        )
                        Text(
                            text = stringResource(R.string.remittances_are_free_with_moneco),
                            fontSize = 14.sp,
                            color = CustomTheme.colors.duskGray
                        )
                        Text(
                            modifier = Modifier.clickable {

                            },
                            text = stringResource(R.string.check_number_of_free_remittance_remaining),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = CustomTheme.colors.ceruleanBlue
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = stringResource(R.string.fees_breakdown),
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
                            DashedLine(
                                lineColor = CustomTheme.colors.slateGray
                            )
                            RecipientAmountRow(
                                text = stringResource(R.string.recipient_gets),
                                calculatedValue = "${
                                    amount.toDoubleOrNull()?.times(conversionRate)
                                }"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            ButtonPrimary(
                                textResId = R.string.send,
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                onClick = {
                                    confirmBottomSheetVisible = true
                                },
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
    transaction: TransactionEntity,
    navController: NavController,
    onConfirm: () -> Unit,
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
                color = CustomTheme.colors.midnightBlue
            )
            ConfirmationRow(
                description = "You're sending",
                text = formatAmountToXOF(transaction.amount!!)
            )
            ConfirmationRow(
                description = "To",
                text = "${transaction.firstName} ${transaction.lastName}"
            )
            ConfirmationRow(
                description = "Via",
                text = "${transaction.mobile_wallet} : ${transaction.country} ${transaction.phoneNumber}"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        onConfirm()
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
    modifier: Modifier = Modifier,
    lineColor: Color
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    Canvas(
        modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = lineColor,
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
            color = CustomTheme.colors.duskGray
        )
        Text(
            text = fee.amount,
            fontSize = 14.sp,
            color = CustomTheme.colors.midnightBlue
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
            color = CustomTheme.colors.duskGray
        )
        Text(
            text = "$calculatedValue XOF",
            fontSize = 18.sp,
            color = CustomTheme.colors.midnightBlue,
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
            color = CustomTheme.colors.duskGray
        )
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = CustomTheme.colors.midnightBlue
        )
    }
}