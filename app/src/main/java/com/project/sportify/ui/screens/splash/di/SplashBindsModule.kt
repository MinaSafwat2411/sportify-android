package com.project.sportify.ui.screens.splash.di

import com.project.sportify.domain.usecases.splash.ISplashUseCase
import com.project.sportify.domain.usecases.splash.SplashUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SplashBindsModule {

    @Binds
    @Singleton
    abstract fun bindSplashUseCase(useCase: SplashUseCase): ISplashUseCase
}