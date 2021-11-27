package ru.ivk1800.tg.feature.auth

import org.drinkless.tdlib.TdApi
import ru.ivk1800.tg.api.TdFunctionExecutor

class TdAuthenticationManager(
    private val functionExecutor: TdFunctionExecutor
) : AuthenticationManager {

    override suspend fun setAuthenticationPhoneNumber(phoneNumber: String) {
        functionExecutor.send<TdApi.Ok>(
            TdApi.SetAuthenticationPhoneNumber().apply {
                this.phoneNumber = phoneNumber
                settings = TdApi.PhoneNumberAuthenticationSettings().apply {
                    allowSmsRetrieverApi = false
                    allowFlashCall = false
                    isCurrentPhoneNumber = false
                }
            }
        )
    }

    override suspend fun checkAuthenticationCode(code: String) {
        functionExecutor.send<TdApi.Ok>(TdApi.CheckAuthenticationCode(code))
    }
}
