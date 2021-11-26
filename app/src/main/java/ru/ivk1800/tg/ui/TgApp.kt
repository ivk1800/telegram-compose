package ru.ivk1800.tg.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.terrakok.cicerone.NavigatorHolder
import ru.ivk1800.tg.feature.FeatureProvider
import ru.ivk1800.tg.navigation.ComposeNavigator
import ru.ivk1800.tg.ui.theme.TgTheme

@Composable
fun TgApp(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    featureProvider: FeatureProvider,
    navigatorHolder: NavigatorHolder,
) {
    TgTheme {
        val navController: NavHostController = rememberNavController()

        val navigator = ComposeNavigator(navController)

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        navigatorHolder.setNavigator(navigator)
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        navigatorHolder.removeNavigator()
                    }
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        NavHost(
            navController = navController,
            startDestination = "hello",
        ) {
            composable(
                "hello",
            ) {
                Hello()
            }
            composable(
                "auth",
            ) {
                featureProvider.featureAuthApi.authScreenFactory.Create()
            }
        }
    }
}

@Composable
fun Hello() {
    Text(text = "Hello")
}
