package ru.ivk1800.tg.feature.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ivk1800.tg.feature.auth.AuthenticationManager

class AuthViewModel(
    private val authenticationManager: AuthenticationManager,
) : ViewModel() {

    private val state =
        MutableStateFlow<AuthUiState>(
            AuthUiState.PhoneState(
                blockInteraction = false,
                title = "Your phone",
            )
        )

    private val clearCodeEvent = LiveEvent<Unit>(config = LiveEventConfig.Normal)

    private var currentPhoneNumber: String? = null

    fun observeState(): StateFlow<AuthUiState> = state.asStateFlow()

    fun observeClearCodeEvent(): LiveData<Unit> = clearCodeEvent

    fun onSubmitPhoneTap(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state.update {
                AuthUiState.PhoneState(
                    blockInteraction = true,
                    title = "Your phone",
                )
            }

            try {
                authenticationManager.setAuthenticationPhoneNumber(phone)
                currentPhoneNumber = phone
                state.update {
                    AuthUiState.CodeState(
                        blockInteraction = false,
                        title = phone,
                    )
                }
            } catch (e: Exception) {
                state.value = AuthUiState.PhoneState(
                    blockInteraction = false,
                    title = "Your phone",
                )
            }
        }
    }

    fun onSubmitCodeTap(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = AuthUiState.CodeState(
                blockInteraction = true,
                title = currentPhoneNumber.orEmpty(),
            )
            try {
                authenticationManager.checkAuthenticationCode(code)
            } catch (e: Exception) {
                clearCodeEvent.value = Unit
                state.value = AuthUiState.CodeState(
                    blockInteraction = false,
                    title = currentPhoneNumber.orEmpty(),
                )
            }
        }
    }
}
