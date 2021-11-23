package ru.ivk1800.tg.feature.auth.api

import androidx.compose.runtime.Composable

interface FeatureAuthApi {
    val authScreenFactory: AuthScreenFactory
}

interface AuthScreenFactory {
    @Composable
    fun Create()
}