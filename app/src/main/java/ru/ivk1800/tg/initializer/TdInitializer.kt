package ru.ivk1800.tg.initializer

import android.content.Context
import androidx.startup.Initializer
import ru.ivk1800.tdlib.TdLibClient
import ru.ivk1800.tg.api.TdFunctionExecutor
import ru.ivk1800.tg.api.provider.update.AuthenticationStateProvider
import ru.ivk1800.tg.app.AppController
import ru.ivk1800.tg.navigation.ComposeRouter
import javax.inject.Inject

class TdInitializer : Initializer<Unit> {

    @Inject
    lateinit var router: ComposeRouter

    @Inject
    lateinit var client: TdLibClient

    @Inject
    lateinit var functionExecutor: TdFunctionExecutor

    @Inject
    lateinit var updatesProvider: AuthenticationStateProvider

    @Inject
    lateinit var appController: AppController

    override fun create(context: Context) {
        val entryPoint = InitializerEntryPoint.resolve(context)
        entryPoint.inject(this)
        entryPoint.getAuthenticationStateProvider()
        appController.init()
        client.init()
        router.toAuth()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(DependencyGraphInitializer::class.java)
    }
}
