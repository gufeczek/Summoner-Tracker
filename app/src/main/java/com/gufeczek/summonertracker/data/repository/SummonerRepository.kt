package com.gufeczek.summonertracker.data.repository

import com.gufeczek.summonertracker.data.model.Summoner

interface SummonerRepository {
    suspend fun getSummoner(summonerName: String, defaultToLocalData: Boolean = true): Summoner?
    suspend fun upsertSummoner(summoner: Summoner?)
}
