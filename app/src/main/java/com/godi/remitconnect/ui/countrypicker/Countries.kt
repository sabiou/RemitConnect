package com.godi.remitconnect.ui.countrypicker

import com.godi.remitconnect.R

/**
 * Represents information about a country.
 *
 * @property countryCode The country code of the country.
 * @property countryPhoneCode The phone code of the country.
 * @property currencyCode The currency code of the country.
 * @property countryName The name of the country.
 * @property flagResID The resource ID of the country's flag.
 */
data class Country(
    var countryCode: String,
    val countryPhoneCode: String = "",
    val currencyCode: String = "",
    val countryName: String = "",
    val flagResID: Int = 0
)

/**
 * Returns the flag resource ID for the given country name.
 *
 * @param countryName The name of the country.
 * @return The flag resource ID for the country.
 */
fun getFlags(countryName: String): Int {
    return when (countryName) {
        "bj" -> R.drawable.flag_benin
        "ma" -> R.drawable.flag_morocco
        "sn" -> R.drawable.flag_senegal
        "tg" -> R.drawable.flag_togo
        else -> R.drawable.flag_benin
    }
}

/**
 * Returns a list of countries.
 *
 * @return The list of countries.
 */
fun getCountriesList(): List<Country> {
    val countries: MutableList<Country> = ArrayList()
    countries.add(Country("bj", "+229", "XOF", "Benin"))
    countries.add(Country("ma", "+212", "MAD","Morocco"))
    countries.add(Country("tg", "+228", "XOF","Togo"))
    countries.add(Country("sn", "+221", "XOF","Senegal"))
    return countries
}