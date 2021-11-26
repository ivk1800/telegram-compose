package ru.ivk1800.tg.impl.provider.update

import kotlinx.coroutines.flow.Flow
import org.drinkless.tdlib.TdApi
import ru.ivk1800.tdlib.TdLibClient
import ru.ivk1800.tg.api.provider.update.UpdatesProvider

class UpdatesProviderImpl(
    private val tdLibClient: TdLibClient,
) : UpdatesProvider {

    init {
        tdLibClient.updates
    }

    override val updates: Flow<TdApi.Update>
        get() = tdLibClient.updates
}
