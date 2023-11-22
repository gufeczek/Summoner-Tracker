package com.gufeczek.summonertracker.core.di

import com.gufeczek.summonertracker.data.local.dao.SummonerDao
import com.gufeczek.summonertracker.data.remote.client.SummonerService
import com.gufeczek.summonertracker.data.repository.SummonerRepository
import com.gufeczek.summonertracker.data.repository.SummonerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSummonerRepository(
        summonerClient: SummonerService,
        summonerDao: SummonerDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SummonerRepository =
        SummonerRepositoryImpl(summonerClient, summonerDao, ioDispatcher)
}
