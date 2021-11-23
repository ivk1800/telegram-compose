package ru.ivk1800.tg.feature.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import ru.ivk1800.compose.observe
import ru.ivk1800.tg.feature.auth.presentation.AuthUiState
import ru.ivk1800.tg.feature.auth.presentation.AuthViewModel
import ru.ivk1800.tg.ui.BlockInteraction

@ExperimentalComposeUiApi
@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val uiState by viewModel.observeState().collectAsState()
    val phoneState = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(uiState.title)
                },
            )
        },
        content = {
            uiState.let { state ->
                when (state) {
                    is AuthUiState.CodeState -> {
                        val codeCellSates: Map<Int, MutableState<String>> = List(5) {
                            it + 1 to rememberSaveable { mutableStateOf("") }
                        }.toMap()

                        val focusRequesters: Map<Int, FocusRequester> = List(5) {
                            it + 1 to remember { FocusRequester() }
                        }.toMap()

                        DisposableEffect(lifecycleOwner) {
                            observe(lifecycleOwner, viewModel.observeClearCodeEvent()) {
                                codeCellSates.values.forEach {
                                    it.value = ""
                                }
                                focusRequesters[1]!!.requestFocus()
                            }
                        }

                        CodeContent(
                            codeCellSates = codeCellSates,
                            focusRequesters = focusRequesters,
                            onSubmitCode = viewModel::onSubmitCodeTap,
                        )
                    }
                    is AuthUiState.PhoneState -> PhoneContent(
                        phoneState = phoneState
                    )
                }
            }
        },
        floatingActionButton = {
            when (uiState) {
                is AuthUiState.CodeState -> Unit
                is AuthUiState.PhoneState -> {
                    FloatingActionButton(
                        onClick = { viewModel.onSubmitPhoneTap(phoneState.value) },
                    ) {
                        Icon(Icons.Filled.ArrowForward, "next")
                    }
                }
            }
        },
    )

    if (uiState.blockInteraction) {
        BlockInteraction()
    }
}

@ExperimentalComposeUiApi
@Composable
fun PhoneContent(
    phoneState: MutableState<String>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = phoneState.value,
            onValueChange = { newPhone ->
                phoneState.value = newPhone
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() })
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun CodeContent(
    codeCellSates: Map<Int, MutableState<String>>,
    focusRequesters: Map<Int, FocusRequester>,
    onSubmitCode: (code: String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    @Composable
    fun RowScope.createCell(
        number: Int,
        focusRequester: FocusRequester,
        onDone: (() -> Unit)? = null,
    ) {
        val textState: MutableState<String> = codeCellSates[number]!!
        TextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            value = textState.value,
            onValueChange = { codeNumber ->
                val currentValue = textState.value
                if (codeNumber.length < 2) {
                    textState.value = codeNumber
                }

                if (number == 5) {
                    if (currentValue != codeNumber) {
                        onDone?.invoke()
                    }
                } else {
                    focusRequesters[number + 1]!!.requestFocus()
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
            ),
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        List(5) {
            val number = it + 1
            createCell(
                number = number,
                focusRequester = focusRequesters[number]!!,
                if (number == 5) {
                    {
                        keyboardController?.hide()
                        onSubmitCode.invoke(
                            codeCellSates.values.joinToString(separator = "") { state -> state.value }
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}