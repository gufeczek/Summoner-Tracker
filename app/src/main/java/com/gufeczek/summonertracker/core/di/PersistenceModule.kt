package com.gufeczek.summonertracker.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.gufeczek.summonertracker.core.util.preference.PreferenceHelper
import com.gufeczek.summonertracker.data.local.SummonerTrackerDatabase
import com.gufeczek.summonertracker.data.local.dao.SummonerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): SummonerTrackerDatabase {
        return Room
            .databaseBuilder(application, SummonerTrackerDatabase::class.java, "SummonerTracker.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSummonerDao(db: SummonerTrackerDatabase): SummonerDao {
        return db.summonerDao()
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(@ApplicationContext context: Context): PreferenceHelper {
        return PreferenceHelper(context)
    }
}
