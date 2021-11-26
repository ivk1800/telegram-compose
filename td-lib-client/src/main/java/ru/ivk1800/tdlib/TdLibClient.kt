package ru.ivk1800.tdlib

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import kotlin.coroutines.suspendCoroutine

class TdLibClient {

    private val _updates = MutableSharedFlow<TdApi.Update>(
        extraBufferCapacity = Int.MAX_VALUE,
    )
    private lateinit var client: Client

    val updates: Flow<TdApi.Update>
        get() = _updates

    fun init() {
        client = Client.create(
            { `object` ->
                if (`object` is TdApi.Update) {
                    _updates.tryEmit(`object`)
                }
            },
            null,
            null,
        )
    }

    suspend fun <T : TdApi.Object> send(obj: TdApi.Function): T {
        return suspendCoroutine { continuation ->
            client.send(obj) { result ->
                if (result is TdApi.Error) {
                    continuation.resumeWith(Result.failure(Exception(result.message)))
                } else {
                    continuation.resumeWith(Result.success(result as T))
                }

            }
        }
    }
}