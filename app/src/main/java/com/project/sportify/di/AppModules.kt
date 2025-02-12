package com.project.sportify.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.sportify.data.local.ILocalDataSource
import com.project.sportify.data.local.LocalDataSource
import com.project.sportify.data.remote.IRemoteDataSource
import com.project.sportify.data.remote.RemoteDataSource
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.data.sharedprefrences.PreferencesDataSource
import com.project.sportify.utils.connection_utils.ConnectionUtils
import com.project.sportify.utils.connection_utils.IConnectionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
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
    @Singleton
    fun provideConnectivity(@ApplicationContext context: Context): IConnectionUtils {
        return ConnectionUtils(context)  // Ensure `ConnectionUtils` implements `IConnectionUtils`
    }
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseFireStore: FirebaseFirestore,
        firebaseDatabase: FirebaseDatabase,
        firebaseStorage: FirebaseStorage
    ): IRemoteDataSource {
        return RemoteDataSource(firebaseAuth, firebaseFireStore, firebaseDatabase, firebaseStorage)
    }
    @Provides
    @Singleton
    fun provideLocalDataSource(): ILocalDataSource {
        return LocalDataSource()
    }
}
