package ru.ivk1800.tg.app

import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider

internal class AuthorizationStateDelegate(
    private val router: AppControllerRouter,
    private val functionExecutor: TdFunctionExecutor,
    private val authenticationStateProvider: AuthenticationStateProvider,
) {

    fun init() {}
}
