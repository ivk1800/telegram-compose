package ru.ivk1800.tg.feature.auth.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.ivk1800.tg.feature.auth.api.AuthScreenFactory
import ru.ivk1800.tg.feature.auth.presentation.AuthViewModel
import ru.ivk1800.tg.feature.auth.ui.AuthScreen

class AuthScreenFactoryImpl(
    private val dependencies: FeatureAuthDependencies
) : AuthScreenFactory {
    @ExperimentalComposeUiApi
    @Composable
    override fun Create() {
        val viewModel: AuthViewModel = viewModel(
            factory = viewModelFactoryTyped {
                AuthViewModel(
                    authenticationManager = dependencies.authenticationManager,
                )
            }
        )

        AuthScreen(
            viewModel = viewModel,
        )
    }
}