package com.gufeczek.summonertracker.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gufeczek.summonertracker.data.model.Summoner
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "summoner")
data class SummonerEntity(
    @PrimaryKey
    @ColumnInfo(name = "puuid")
    val id: String,

    @ColumnInfo(name = "summoner_name")
    val summonerName: String,

    @ColumnInfo(name = "summoner_level")
    val summonerLevel: Int,

    @ColumnInfo(name = "profile_icon_id")
    val profileIconId: Int,

    @ColumnInfo(name = "revision_date")
    val revisionDate: Long
) {
    fun asDomain() = Summoner(
        id = this.id,
        summonerName = this.summonerName,
        summonerLevel = this.summonerLevel,
        profileIconId = this.profileIconId,
        revisionDate = Instant.fromEpochMilliseconds(this.revisionDate).toLocalDateTime(TimeZone.UTC)
    )
}
