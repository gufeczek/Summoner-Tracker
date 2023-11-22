package com.gufeczek.summonertracker.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gufeczek.summonertracker.data.local.entity.SummonerEntity

@Dao
interface SummonerDao {
    @Query("SELECT * FROM summoner WHERE summoner_name = :summonerName")
    suspend fun getSummoner(summonerName: String): SummonerEntity?

    @Upsert
    suspend fun upsertSummoner(summonerEntity: SummonerEntity)
}
