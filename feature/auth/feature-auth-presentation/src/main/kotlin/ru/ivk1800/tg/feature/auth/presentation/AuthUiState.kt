package ru.ivk1800.tg.feature.auth.presentation

sealed class AuthUiState {

    abstract val blockInteraction: Boolean

    abstract val title: String

    data class PhoneState(
        override val blockInteraction: Boolean,
        override val title: String,
    ) : AuthUiState()

    data class CodeState(
        override val blockInteraction: Boolean,
        override val title: String,
    ) : AuthUiState()
}
