package ru.ivk1800.tg.app

import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider

class AppControllerComponent(
    private val dependencies: AppControllerComponentDependencies,
) {
    val appController: AppController by lazy {
        AppControllerImpl(
            authorizationStateDelegate = dependencies.run {
                AuthorizationStateDelegate(
                    functionExecutor = functionExecutor,
                    authenticationStateProvider = authenticationStateProvider,
                    router = router,
                )
            },
            authenticationStateProvider = dependencies.authenticationStateProvider,
        )
    }
}

interface AppControllerRouter {
    fun toLogin()

    fun toRoot()
}

class AppControllerComponentDependencies(
    val router: AppControllerRouter,
    val functionExecutor: TdFunctionExecutor,
    val authenticationStateProvider: AuthenticationStateProvider,
)