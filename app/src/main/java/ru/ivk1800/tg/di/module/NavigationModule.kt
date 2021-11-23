package ru.ivk1800.tg.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.navigation.ComposeRouter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<ComposeRouter> = Cicerone.create(ComposeRouter())

    @Provides
    @Singleton
    fun provideComposeRouter(cicerone: Cicerone<ComposeRouter>): ComposeRouter = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<ComposeRouter>): NavigatorHolder =
        cicerone.getNavigatorHolder()
}