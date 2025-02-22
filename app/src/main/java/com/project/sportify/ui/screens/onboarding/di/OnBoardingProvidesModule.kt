package com.project.sportify.ui.screens.onboarding.di

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.repositories.onboarding.IOnBoardingRepository
import com.project.sportify.data.repositories.onboarding.OnBoardingRepository
import com.project.sportify.data.repositories.splash.ISplashRepository
import com.project.sportify.data.repositories.splash.SplashRepository
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.utils.connection_utils.IConnectionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardingProvidesModule {

    @Provides
    @Singleton
    fun provideOnBoardingRepository(
        connectionUtils: IConnectionUtils,
        remoteDataSource: IRemoteDataSource,
        localDataSource: ILocalDataSource,
        preferencesDataSource: IPreferencesDataSource
    ): IOnBoardingRepository {
        return OnBoardingRepository(
            connectionUtils,
            remoteDataSource,
            localDataSource,
            preferencesDataSource
        )
    }
}




