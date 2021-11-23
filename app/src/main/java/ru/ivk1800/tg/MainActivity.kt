package ru.ivk1800.tg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import ru.ivk1800.tg.feature.FeatureProvider
import ru.ivk1800.tg.navigation.ComposeNavigator
import ru.ivk1800.tg.navigation.ComposeRouter
import ru.ivk1800.tg.ui.TgApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var featureProvider: FeatureProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TgApp(
                featureProvider = featureProvider,
                navigatorHolder = navigatorHolder,
            )
        }
    }
}