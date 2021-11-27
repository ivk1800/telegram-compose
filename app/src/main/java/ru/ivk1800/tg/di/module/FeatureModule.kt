package ru.ivk1800.tg.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.feature.auth.AuthenticationManager
import ru.ivk1800.tg.feature.auth.api.FeatureAuthApi
import ru.ivk1800.tg.feature.auth.impl.FeatureAuthDependencies
import ru.ivk1800.tg.feature.auth.impl.FeatureAuthImpl
import ru.ivk1800.tg.feature.mainscreen.api.FeatureMainScreenApi
import ru.ivk1800.tg.feature.mainscreen.impl.FeatureMainScreenDependencies
import ru.ivk1800.tg.feature.mainscreen.impl.FeatureMainScreenImpl

@Module
@InstallIn(SingletonComponent::class)
object FeatureModule {

    // region dependencies

    @Provides
    fun provideFeatureAuthApi(
        dependencies: FeatureAuthDependencies
    ): FeatureAuthApi = FeatureAuthImpl(dependencies)

    @Provides
    fun provideFeatureMainScreenApi(
        dependencies: FeatureMainScreenDependencies
    ): FeatureMainScreenApi = FeatureMainScreenImpl(dependencies)

    // endregion dependencies

    // region features

    @Provides
    fun provideFeatureAuthDependencies(
        authenticationManager: AuthenticationManager
    ): FeatureAuthDependencies = FeatureAuthDependencies(
        authenticationManager = authenticationManager,
    )

    @Provides
    fun provideFeatureMainScreenDependencies(): FeatureMainScreenDependencies =
        FeatureMainScreenDependencies()

    // endregion features
}
