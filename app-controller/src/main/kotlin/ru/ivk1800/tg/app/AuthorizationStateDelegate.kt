package ru.ivk1800.tg.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.AuthorizationStateWaitTdlibParameters
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.logger.TdLogger

internal class AuthorizationStateDelegate(
    private val router: AppControllerRouter,
    private val functionExecutor: TdFunctionExecutor,
    private val authenticationStateProvider: AuthenticationStateProvider,
    private val scope: CoroutineScope,
    private val logger: TdLogger,
    private val deviceInfoProvider: DeviceInfoProvider
) {

    fun init() {
        scope.launch(Dispatchers.IO) {
            authenticationStateProvider
                .onAuthorizationStateChange
                .collect {
                    when (it) {
                        is AuthorizationStateWaitTdlibParameters -> {
                            handleWaitTdlibParametersState()
                        }
                        is TdApi.AuthorizationStateWaitEncryptionKey -> {
                            functionExecutor.send<TdApi.Ok>(TdApi.CheckDatabaseEncryptionKey())
                        }
                    }
                }
        }
    }

    private suspend fun handleWaitTdlibParametersState() {
        functionExecutor.send<TdApi.Ok>(
            TdApi.SetTdlibParameters(
                TdApi.TdlibParameters().apply {
                    useTestDc = false
                    useSecretChats = false
                    useMessageDatabase = true
                    useFileDatabase = true
                    useChatInfoDatabase = true
                    ignoreFileNames = true
                    enableStorageOptimizer = true
                    filesDirectory = deviceInfoProvider.filesDirectory.path
                    databaseDirectory = deviceInfoProvider.filesDirectory.path
                    systemLanguageCode = "en"
                    deviceModel = "android"
                    applicationVersion = "1.0.0"
                    apiId = 0
                    apiHash = ""
                }
            )
        )
    }
}
