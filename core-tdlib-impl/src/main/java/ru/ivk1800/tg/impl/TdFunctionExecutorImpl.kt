package ru.ivk1800.tg.impl

import org.drinkless.tdlib.TdApi
import ru.ivk1800.tdlib.TdLibClient
import ru.ivk1800.tg.api.TdFunctionExecutor

class TdFunctionExecutorImpl(
    private val client: TdLibClient,
) : TdFunctionExecutor {

    override suspend fun <T : TdApi.Object> send(obj: TdApi.Function): T =
        client.send(obj)

    override suspend fun <T : TdApi.Object> execute(obj: TdApi.Function): T {
        TODO("Not yet implemented")
    }
}
