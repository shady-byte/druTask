package com.example.drutask.di

import android.content.Context
import androidx.room.Room
import com.example.drutask.data.dataSources.LocalDataSource
import com.example.drutask.data.dataSources.RemoteDataSource
import com.example.drutask.data.dataSources.localDataSource.DatabaseDao
import com.example.drutask.data.dataSources.localDataSource.LocalDataSourceImp
import com.example.drutask.data.dataSources.localDataSource.MoviesDatabase
import com.example.drutask.data.dataSources.remoteDataSource.ApiUrls
import com.example.drutask.data.dataSources.remoteDataSource.MoviesApi
import com.example.drutask.data.dataSources.remoteDataSource.RemoteDataSourceImp
import com.example.drutask.data.repository.MoviesRepositoryImp
import com.example.drutask.domain.repository.MoviesRepository
import com.example.drutask.base.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(ApiUrls.MOVIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            "movies_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDatabaseDao(database: MoviesDatabase): DatabaseDao {
        return database.databaseDao
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(databaseDao: DatabaseDao): LocalDataSource {
        return LocalDataSourceImp(databaseDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(moviesAPi: MoviesApi): RemoteDataSource {
        return RemoteDataSourceImp(moviesAPi)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        sharedPreferencesManager: SharedPreferencesManager
    ): MoviesRepository {
        return MoviesRepositoryImp(localDataSource, remoteDataSource, sharedPreferencesManager)
    }
}