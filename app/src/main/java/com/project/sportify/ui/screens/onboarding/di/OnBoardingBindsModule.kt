package com.project.sportify.ui.screens.onboarding.di

import com.project.sportify.domain.usecases.onboarding.IOnBoardingUseCase
import com.project.sportify.domain.usecases.onboarding.OnBoardingUseCase
import com.project.sportify.domain.usecases.splash.ISplashUseCase
import com.project.sportify.domain.usecases.splash.SplashUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnBoardingBindsModule {

    @Binds
    @Singleton
    abstract fun bindOnBoardingUseCase(useCase: OnBoardingUseCase): IOnBoardingUseCase
}