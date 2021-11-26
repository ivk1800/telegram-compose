package ru.ivk1800.tg.feature

import ru.ivk1800.tg.feature.auth.api.FeatureAuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureProvider @Inject constructor(
    private val featureFactory: FeatureFactory,
) {
    val featureAuthApi: FeatureAuthApi by lazy { featureFactory.createFeatureAuthApi() }
}
