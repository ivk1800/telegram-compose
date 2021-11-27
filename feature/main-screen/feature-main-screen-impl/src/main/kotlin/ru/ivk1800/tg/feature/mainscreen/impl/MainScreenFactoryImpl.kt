package ru.ivk1800.tg.feature.mainscreen.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import ru.ivk1800.tg.feature.mainscreen.api.MainScreenFactory
import ru.ivk1800.tg.feature.mainscreen.ui.MainScreen

internal class MainScreenFactoryImpl(
    private val dependencies: FeatureMainScreenDependencies
) : MainScreenFactory {
    @ExperimentalComposeUiApi
    @Composable
    override fun Create() {
        return MainScreen()
    }
}
