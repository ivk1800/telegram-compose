package ru.ivk1800.tg.navigation

import androidx.navigation.NavHostController
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Navigator

class ComposeNavigator(
    private val navController: NavHostController,
) : Navigator {
    override fun applyCommands(commands: Array<out Command>) {
        for (command in commands) {
            when (command) {
                is Forward -> {
                    navController.navigate(command.screen.screenKey ) {
                        popUpTo(0)
                    }
                }
            }
        }
    }
}