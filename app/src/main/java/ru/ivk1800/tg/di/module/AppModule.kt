package ru.ivk1800.tg.di.module

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.app.AppController
import ru.ivk1800.tg.app.AppControllerComponent
import ru.ivk1800.tg.app.AppControllerComponentDependencies
import ru.ivk1800.tg.app.AppControllerRouter
import ru.ivk1800.tg.app.DeviceInfoProvider
import ru.ivk1800.tg.logger.TdLogger
import java.io.File
import java.nio.file.Path
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppController(
        functionExecutor: TdFunctionExecutor,
        authenticationStateProvider: AuthenticationStateProvider,
        @ApplicationContext context: Context,
        logger: TdLogger,
    ): AppController =
        AppControllerComponent(
            dependencies = AppControllerComponentDependencies(
                functionExecutor = functionExecutor,
                logger = logger,
                authenticationStateProvider = authenticationStateProvider,
                router = object : AppControllerRouter {
                    override fun toLogin() {
                        TODO("Not yet implemented")
                    }

                    override fun toRoot() {
                        TODO("Not yet implemented")
                    }
                },
                deviceInfoProvider = object : DeviceInfoProvider {
                    override val filesDirectory: File
                        get() = context.filesDir
                }
            )
        ).appController

    @Provides
    @Singleton
    fun provideTdLogger(): TdLogger =
        object : TdLogger {
            override fun d(tag: String, message: String) {
                Log.d(tag, message)
            }
        }
}
