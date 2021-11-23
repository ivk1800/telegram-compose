package ru.ivk1800.tg.initializer

import android.content.Context
import androidx.startup.Initializer
import ru.ivk1800.tg.navigation.ComposeRouter
import javax.inject.Inject

class TdInitializer : Initializer<Unit> {

    @Inject
    lateinit var router: ComposeRouter

    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context).inject(this)
        router.toAuth()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(DependencyGraphInitializer::class.java)
    }
}
