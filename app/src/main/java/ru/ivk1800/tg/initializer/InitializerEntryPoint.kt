package ru.ivk1800.tg.initializer

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InitializerEntryPoint {

    fun inject(initializer: TdInitializer)

    fun getAuthenticationStateProvider(): AuthenticationStateProvider

    companion object {
        fun resolve(context: Context): InitializerEntryPoint {
            val appContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(
                appContext,
                InitializerEntryPoint::class.java,
            )
        }
    }
}