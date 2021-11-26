package ru.ivk1800.tg.api

import org.drinkless.tdlib.TdApi

interface TdFunctionExecutor {
    suspend fun <T : TdApi.Object> send(obj: TdApi.Function): T

    suspend fun <T : TdApi.Object> execute(obj: TdApi.Function): T
}
