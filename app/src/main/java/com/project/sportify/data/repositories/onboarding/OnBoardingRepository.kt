package com.project.sportify.data.repositories.onboarding

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.repositories.base.BaseRepository
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.utils.connection_utils.IConnectionUtils
import javax.inject.Inject

class OnBoardingRepository @Inject constructor(
    private  val mConnectionUtils: IConnectionUtils,
    private  val mRemoteDataSource: IRemoteDataSource,
    private  val mLocalDataSource: ILocalDataSource,
    private val mPreferencesDataSource: IPreferencesDataSource,
): BaseRepository(mConnectionUtils, mRemoteDataSource, mPreferencesDataSource, mLocalDataSource),
IOnBoardingRepository{
    override fun open() {
        mPreferencesDataSource.open()
    }
}