package ru.ivk1800.compose

import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> DisposableEffectScope.observe(
    lifecycleOwner: LifecycleOwner,
    liveData: LiveData<T>,
    onChanged: ((value: T) -> Unit),
): DisposableEffectResult {
    val observer = Observer<T> { onChanged.invoke(it) }
    liveData.observe(
        lifecycleOwner,
        observer,
    )

    return onDispose {
        liveData.removeObserver(observer)
    }
}
