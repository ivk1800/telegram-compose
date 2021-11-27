package ru.ivk1800.tg.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.MainScope
import ru.ivk1800.tdlib.TdLibClient
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.api.provider.update.UpdatesProvider
import ru.ivk1800.tg.impl.TdFunctionExecutorImpl
import ru.ivk1800.tg.impl.provider.update.AuthenticationStateProviderImpl
import ru.ivk1800.tg.impl.provider.update.UpdatesProviderImpl
import ru.ivk1800.tg.logger.TdLogger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TdLibModule {

    @Provides
    @Singleton
    fun provideAuthenticationStateUpdatesProvider(
        updatesProvider: UpdatesProvider,
        logger: TdLogger
    ): AuthenticationStateProvider = AuthenticationStateProviderImpl(
        updatesProvider,
        logger,
        MainScope(),
    )

    @Provides
    @Singleton
    fun provideTdFunctionExecutor(client: TdLibClient): TdFunctionExecutor =
        TdFunctionExecutorImpl(client)

    @Provides
    @Singleton
    fun provideUpdatesProviderImpl(client: TdLibClient) = UpdatesProviderImpl(client)

    @Provides
    @Singleton
    fun provideUpdatesProvider(impl: UpdatesProviderImpl): UpdatesProvider = impl

    @Provides
    @Singleton
    fun provideClient(): TdLibClient = TdLibClient()
}
