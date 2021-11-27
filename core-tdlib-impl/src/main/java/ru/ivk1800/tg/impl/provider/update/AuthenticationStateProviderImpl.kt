package ru.ivk1800.tg.impl.provider.update

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.AuthorizationStateClosed
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.api.provider.update.UpdatesProvider
import ru.ivk1800.tg.logger.TdLogger

class AuthenticationStateProviderImpl(
    private val updatesProvider: UpdatesProvider,
    private val logger: TdLogger,
    scope: CoroutineScope,
) : AuthenticationStateProvider {

    private val _updates = MutableSharedFlow<TdApi.AuthorizationState>(
        extraBufferCapacity = Int.MAX_VALUE,
    )

    @Volatile
    private var _current: TdApi.AuthorizationState = AuthorizationStateClosed()

    init {
        scope.launch(Dispatchers.IO) {
            updatesProvider.updates
                .filterIsInstance<TdApi.UpdateAuthorizationState>()
                .collect { update ->
                    _current = update.authorizationState
                    check(_updates.tryEmit(_current))
                }
        }
    }

    override val onAuthorizationStateChange: Flow<TdApi.AuthorizationState>
        get() = _updates

    override val current: TdApi.AuthorizationState
        get() = _current
}
