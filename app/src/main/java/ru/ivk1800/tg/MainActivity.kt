package ru.ivk1800.tg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.hilt.android.AndroidEntryPoint
import ru.ivk1800.tg.app.AppController
import ru.ivk1800.tg.feature.FeatureProvider
import ru.ivk1800.tg.ui.TgApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var featureProvider: FeatureProvider

    @Inject lateinit var appController: AppController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            appController.readyForInteraction()
        }

        setContent {
            TgApp(
                featureProvider = featureProvider,
                navigatorHolder = navigatorHolder,
            )
        }
    }
}