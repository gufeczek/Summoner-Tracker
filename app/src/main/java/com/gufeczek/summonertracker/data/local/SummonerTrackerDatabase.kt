package com.gufeczek.summonertracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gufeczek.summonertracker.data.local.dao.SummonerDao
import com.gufeczek.summonertracker.data.local.entity.SummonerEntity

@Database(
    entities = [SummonerEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SummonerTrackerDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
}
