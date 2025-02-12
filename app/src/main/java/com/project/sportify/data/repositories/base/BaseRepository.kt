package com.project.sportify.data.repositories.base

import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.domain.base.IBaseRepository
import com.project.sportify.utils.connection_utils.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

abstract class BaseRepository(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    private  val mLocalDataSource: ILocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IBaseRepository {

}
