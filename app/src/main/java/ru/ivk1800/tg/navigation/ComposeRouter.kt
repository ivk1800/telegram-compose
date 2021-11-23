package ru.ivk1800.tg.navigation

import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Screen

class ComposeRouter : BaseRouter() {

    fun toAuth() {
        executeCommands(
            Forward(
                object : Screen {
                    override val screenKey: String
                        get() = "Auth"
                }
            ),
        )
    }
}