package ru.ivk1800.tg.feature.auth

interface AuthenticationManager {
    suspend fun setAuthenticationPhoneNumber(phoneNumber: String)

    suspend fun checkAuthenticationCode(code: String)
}
