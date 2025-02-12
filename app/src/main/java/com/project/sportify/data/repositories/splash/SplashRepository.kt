package com.project.sportify.data.repositories.splash

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.repositories.base.BaseRepository
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.di.IoDispatcher
import com.project.sportify.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SplashRepository @Inject constructor(
    connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource, dispatcher),
    ISplashRepository {
    override fun shouldNavigateToWhichScreen(): Boolean {
        return mIPreferencesDataSource.shouldNavigateToWhichScreen()
    }
}