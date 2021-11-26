package ru.ivk1800.tg.api.provider.update

import kotlinx.coroutines.flow.Flow
import org.drinkless.tdlib.TdApi

interface AuthenticationStateProvider {
    val onAuthorizationStateChange: Flow<TdApi.AuthorizationState>

    val current: TdApi.AuthorizationState
}