package com.project.sportify.ui.screens.splash.di

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.remote.RemoteDataSource
import com.project.sportify.data.repositories.splash.ISplashRepository
import com.project.sportify.data.repositories.splash.SplashRepository
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.domain.usecases.splash.ISplashUseCase
import com.project.sportify.domain.usecases.splash.SplashUseCase
import com.project.sportify.utils.connection_utils.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SplashProvidesModule {

    @Provides
    @Singleton
    fun provideSplashRepository(
        connectionUtils: IConnectionUtils,
        remoteDataSource: IRemoteDataSource,
        localDataSource: ILocalDataSource,
        preferencesDataSource: IPreferencesDataSource
    ): ISplashRepository {
        return SplashRepository(
            connectionUtils,
            remoteDataSource,
            localDataSource,
            preferencesDataSource
        )
    }
}




