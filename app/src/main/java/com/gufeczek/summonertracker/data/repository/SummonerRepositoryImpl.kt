package com.gufeczek.summonertracker.data.repository

import android.util.Log
import com.gufeczek.summonertracker.core.di.IoDispatcher
import com.gufeczek.summonertracker.core.util.extension.onError
import com.gufeczek.summonertracker.core.util.extension.onException
import com.gufeczek.summonertracker.core.util.extension.onSuccess
import com.gufeczek.summonertracker.data.local.dao.SummonerDao
import com.gufeczek.summonertracker.data.model.Summoner
import com.gufeczek.summonertracker.data.remote.client.SummonerService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepositoryImpl @Inject constructor(
    private val summonerService: SummonerService,
    private val summonerDao: SummonerDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SummonerRepository {
    override suspend fun getSummoner(summonerName: String, defaultToLocalData: Boolean): Summoner? = withContext(ioDispatcher) {
        val summonerCache = summonerDao.getSummoner(summonerName = summonerName)

        if (summonerCache == null) {
            val summonerResponse = summonerService.getSummoner(summonerName = summonerName)
            summonerResponse.onSuccess { summoner ->
                return@onSuccess summonerDao.upsertSummoner(summoner.asDomain().asEntity())
            }.onError { code, message ->
                Log.d(TAG, "Error: $code - $message")
            }.onException {
                Log.d(TAG, "Exception: $it")
            }
        }
        summonerCache?.asDomain()
    }

    override suspend fun upsertSummoner(summoner: Summoner?): Unit = withContext(ioDispatcher) {
        summoner?.let { summonerDao.upsertSummoner(summonerEntity = summoner.asEntity()) }
    }

    companion object {
        const val TAG = "SummonerRepositoryImpl"
    }
}
