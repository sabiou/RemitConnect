package com.godi.remitconnect.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

const val CONVERSION_RATE = 655.94


/**
 * Formats the given amount in Euro (EUR) currency.
 *
 * @param amount The amount to be formatted, of type Double.
 * @return A string representation of the formatted amount in Euro (EUR) currency.
 */
fun formatCurrency(amount: Double): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE)
    currencyFormat.maximumFractionDigits = 0
    currencyFormat.currency = Currency.getInstance("EUR")
    return currencyFormat.format(amount)
}

/**
 * Formats the given amount in US Dollar (USD) currency.
 *
 * @param amount The amount to be formatted, of type Double.
 * @return A string representation of the formatted amount in US Dollar (USD) currency.
 */
fun formatCurrencyToUsd(amount: Double): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
    currencyFormat.maximumFractionDigits = 0
    currencyFormat.currency = Currency.getInstance("USD")
    return currencyFormat.format(amount)
}

/**
 * Formats the given amount in West African CFA franc (XOF) currency.
 *
 * @param amount The amount to be formatted, of type Double.
 * @return A string representation of the formatted amount in West African CFA franc (XOF) currency.
 */
fun formatAmountToXOF(amount: Double): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("fr", "XOF"))
    currencyFormat.maximumFractionDigits = 0
    currencyFormat.currency = Currency.getInstance("XOF")
    return currencyFormat.format(euroToXof(amount))
}

/**
 * Converts the given amount from Euro (EUR) to West African CFA franc (XOF) using the defined conversion rate.
 *
 * @param amount The amount to be converted, of type Double.
 * @return The converted amount in West African CFA franc (XOF) currency, of type Double.
 */
fun euroToXof(amount: Double): Double {
    return amount.times(CONVERSION_RATE)
}