package ru.ivk1800.tg.app

import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider

internal class AppControllerImpl(
    private val authorizationStateDelegate: AuthorizationStateDelegate,
    private val authenticationStateProvider: AuthenticationStateProvider,
) : AppController {
    override fun init() {
        authorizationStateDelegate.init()
    }

    override fun readyForInteraction() {}
}