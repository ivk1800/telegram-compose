package ru.ivk1800.tg.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.logger.TdLogger
import kotlin.coroutines.EmptyCoroutineContext

class AppControllerComponent(
    private val dependencies: AppControllerComponentDependencies,
) {

    private val scope = CoroutineScope(SupervisorJob() + EmptyCoroutineContext)

    val appController: AppController by lazy {
        AppControllerImpl(
            authorizationStateDelegate = dependencies.run {
                AuthorizationStateDelegate(
                    functionExecutor = functionExecutor,
                    authenticationStateProvider = authenticationStateProvider,
                    router = router,
                    scope = scope,
                    logger = logger,
                    deviceInfoProvider = deviceInfoProvider,
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
    val deviceInfoProvider: DeviceInfoProvider,
    val logger: TdLogger,
)
