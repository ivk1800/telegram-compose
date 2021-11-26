package ru.ivk1800.tg.feature

import ru.ivk1800.tg.feature.auth.api.FeatureAuthApi
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FeatureFactory @Inject constructor(
    private val featureAuthApiProvider: Provider<FeatureAuthApi>
) {
    fun createFeatureAuthApi(): FeatureAuthApi = featureAuthApiProvider.get()
}
