package com.project.sportify.data.repositories.splash

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.repositories.base.BaseRepository
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.utils.connection_utils.IConnectionUtils
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource,
    private val preferencesDataSource: IPreferencesDataSource
) : BaseRepository(connectionUtils, remoteDataSource, preferencesDataSource, localDataSource),
    ISplashRepository {
    override fun shouldNavigateToWhichScreen(): Boolean {
        return preferencesDataSource.shouldNavigateToWhichScreen()
    }
}