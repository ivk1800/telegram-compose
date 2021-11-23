package ru.ivk1800.tg.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import ru.ivk1800.tg.feature.auth.AuthenticationManager

@Module
@InstallIn(SingletonComponent::class)
object ComponentModule {

    @Provides
    fun provideAuthenticationManager(): AuthenticationManager =
        object : AuthenticationManager {
            override suspend fun setAuthenticationPhoneNumber(phoneNumber: String) {
                delay(300)
                if (phoneNumber != "1") {
                    throw ArithmeticException()
                }
            }

            override suspend fun checkAuthenticationCode(code: String) {
                delay(300)
                if (code != "11111") {
                    throw ArithmeticException()
                }
            }
        }
}