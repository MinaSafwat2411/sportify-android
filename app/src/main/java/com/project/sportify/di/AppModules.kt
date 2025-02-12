package com.project.sportify.di

import PreferencesDataSource
import android.content.Context
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.remote.RemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.local.LocalDataSource
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.firebase.FireBaseInterface
import com.project.sportify.utils.connection_utils.ConnectionUtils
import com.project.sportify.utils.connection_utils.IConnectionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideConnectivity(@ApplicationContext context: Context): IConnectionUtils {
        return ConnectionUtils(context)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(): ILocalDataSource {
        return LocalDataSource()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        firebaseInterface: FireBaseInterface,
    ): IRemoteDataSource {
        return RemoteDataSource(firebaseInterface)
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(
        @ApplicationContext context: Context,
        gson: Gson
    ): IPreferencesDataSource {
        return PreferencesDataSource(context, gson)
    }

    @Provides
    @IoDispatcher
    fun provideDispatcher(
    ): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(
    ): CoroutineDispatcher {
        return Dispatchers.Main
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher