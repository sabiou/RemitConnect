package com.godi.remitconnect

import com.godi.remitconnect.utils.CONVERSION_RATE
import com.godi.remitconnect.utils.euroToXof
import com.godi.remitconnect.utils.formatAmountToXOF
import com.godi.remitconnect.utils.formatCurrency
import com.godi.remitconnect.utils.formatCurrencyToUsd
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class CurrencyUtilsTest {
    @Test
    fun testFormatCurrency() {
        val amount = 100.0
        val formattedAmount = formatCurrency(amount)
        val expectedFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE)
        expectedFormat.maximumFractionDigits = 0
        expectedFormat.currency = Currency.getInstance("EUR")
        val expectedFormattedAmount = expectedFormat.format(amount)
        assertEquals(expectedFormattedAmount, formattedAmount)
    }

    @Test
    fun testFormatCurrencyToUsd() {
        val amount = 100.0
        val formattedAmount = formatCurrencyToUsd(amount)
        val expectedFormat = NumberFormat.getCurrencyInstance(Locale.US)
        expectedFormat.maximumFractionDigits = 0
        expectedFormat.currency = Currency.getInstance("USD")
        val expectedFormattedAmount = expectedFormat.format(amount)
        assertEquals(expectedFormattedAmount, formattedAmount)
    }

    @Test
    fun testFormatAmountToXOF() {
        val amount = 100.0
        val formattedAmount = formatAmountToXOF(amount)
        val expectedFormat = NumberFormat.getCurrencyInstance(Locale("fr", "XOF"))
        expectedFormat.maximumFractionDigits = 0
        expectedFormat.currency = Currency.getInstance("XOF")
        val expectedFormattedAmount = expectedFormat.format(euroToXof(amount))
        assertEquals(expectedFormattedAmount, formattedAmount)
    }

    @Test
    fun testEuroToXof() {
        val amount = 100.0
        val convertedAmount = euroToXof(amount)
        val expectedAmount = amount.times(CONVERSION_RATE)
        assertEquals(expectedAmount, convertedAmount, 0.001)
    }
}