package ru.ivk1800.tg.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.app.AppController
import ru.ivk1800.tg.app.AppControllerComponent
import ru.ivk1800.tg.app.AppControllerComponentDependencies
import ru.ivk1800.tg.app.AppControllerRouter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppController(
        functionExecutor: TdFunctionExecutor,
        authenticationStateProvider: AuthenticationStateProvider,
    ): AppController =
        AppControllerComponent(
            dependencies = AppControllerComponentDependencies(
                functionExecutor = functionExecutor,
                authenticationStateProvider = authenticationStateProvider,
                router = object : AppControllerRouter {
                    override fun toLogin() {
                        TODO("Not yet implemented")
                    }

                    override fun toRoot() {
                        TODO("Not yet implemented")
                    }
                },
            )
        ).appController
}
