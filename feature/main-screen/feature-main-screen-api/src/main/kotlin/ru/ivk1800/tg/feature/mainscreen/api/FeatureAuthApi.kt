package ru.ivk1800.tg.feature.mainscreen.api

import androidx.compose.runtime.Composable

interface FeatureMainScreenApi {
    val mainScreenFactory: MainScreenFactory
}

interface MainScreenFactory {
    @Composable
    fun Create()
}
