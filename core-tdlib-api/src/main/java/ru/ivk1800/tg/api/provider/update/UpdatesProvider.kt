package ru.ivk1800.tg.api.provider.update

import kotlinx.coroutines.flow.Flow
import org.drinkless.tdlib.TdApi

interface UpdatesProvider {
    val updates: Flow<TdApi.Update>
}
