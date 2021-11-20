package ru.ivk1800.tg.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ivk1800.tg.ui.theme.TgTheme

@Composable
fun TgApp() {
    TgTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "hello",
        ) {
            composable(
                "hello",
            ) { Hello() }
        }
    }
}

@Composable
fun Hello() {
    Text(text = "Hello")
}