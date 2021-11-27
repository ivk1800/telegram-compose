package ru.ivk1800.tg.feature.mainscreen.impl

import ru.ivk1800.tg.feature.mainscreen.api.FeatureMainScreenApi
import ru.ivk1800.tg.feature.mainscreen.api.MainScreenFactory

class FeatureMainScreenImpl(
    private val dependencies: FeatureMainScreenDependencies,
) : FeatureMainScreenApi {
    override val mainScreenFactory: MainScreenFactory by lazy { MainScreenFactoryImpl(dependencies) }
}

class FeatureMainScreenDependencies
