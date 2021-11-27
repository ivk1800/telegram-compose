package ru.ivk1800.tg.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.feature.auth.AuthenticationManager
import ru.ivk1800.tg.feature.auth.TdAuthenticationManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComponentModule {

    @Provides
    @Singleton
    fun provideAuthenticationManager(functionExecutor: TdFunctionExecutor): AuthenticationManager =
        TdAuthenticationManager(functionExecutor)
}
