package ru.ivk1800.tg.feature.auth.impl

import ru.ivk1800.tg.feature.auth.AuthenticationManager
import ru.ivk1800.tg.feature.auth.api.AuthScreenFactory
import ru.ivk1800.tg.feature.auth.api.FeatureAuthApi

class FeatureAuthImpl(
    private val dependencies: FeatureAuthDependencies,
) : FeatureAuthApi {
    override val authScreenFactory: AuthScreenFactory by lazy { AuthScreenFactoryImpl(dependencies) }
}

class FeatureAuthDependencies(
    val authenticationManager: AuthenticationManager,
)