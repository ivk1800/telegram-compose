package ru.ivk1800.tg.feature

import ru.ivk1800.tg.feature.auth.api.FeatureAuthApi
import ru.ivk1800.tg.feature.mainscreen.api.FeatureMainScreenApi
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FeatureFactory @Inject constructor(
    private val featureAuthApiProvider: Provider<FeatureAuthApi>,
    private val featureMainScreenApiProvider: Provider<FeatureMainScreenApi>
) {
    fun createFeatureAuthApi(): FeatureAuthApi = featureAuthApiProvider.get()
    fun createFeatureMainScreenApi(): FeatureMainScreenApi = featureMainScreenApiProvider.get()
}
