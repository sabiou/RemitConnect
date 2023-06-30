package com.godi.remitconnect.ui.countrypicker

import com.godi.remitconnect.R

data class CountryCode(
    var countryCode: String,
    val countryPhoneCode: String = "",
    val currencyCode: String = "",
    val countryName: String = "",
    val flagResID: Int = 0
)

fun getFlags(countryName: String): Int {
    return when (countryName) {
        "bj" -> R.drawable.flag_benin
        "ma" -> R.drawable.flag_morocco
        "sn" -> R.drawable.flag_senegal
        "tg" -> R.drawable.flag_togo
        else -> R.drawable.flag_benin
    }
}

fun getCountriesList(): List<CountryCode> {
    val countries: MutableList<CountryCode> = ArrayList()
    countries.add(CountryCode("bj", "+229", "XOF", "Benin"))
    countries.add(CountryCode("ma", "+212", "MAD","Morocco"))
    countries.add(CountryCode("tg", "+228", "XOF","Togo"))
    countries.add(CountryCode("sn", "+221", "XOF","Senegal"))
    return countries
}