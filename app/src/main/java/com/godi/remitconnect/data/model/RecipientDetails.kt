package com.godi.remitconnect.data.model

data class RecipientDetails(
    val selectedCountryCode: String? = "",
    val phoneNumber: String?,
    val firstName: String?,
    val lastName: String?,
    val mobileWallet: String? = ""
)
