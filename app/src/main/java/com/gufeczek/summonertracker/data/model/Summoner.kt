package com.gufeczek.summonertracker.data.model

import com.gufeczek.summonertracker.data.local.entity.SummonerEntity
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

data class Summoner(
    val id: String,
    val summonerName: String,
    val summonerLevel: Int,
    val profileIconId: Int,
    val revisionDate: LocalDateTime
) {
    fun asEntity() = SummonerEntity(
        id = this.id,
        summonerName = this.summonerName,
        summonerLevel = this.summonerLevel,
        profileIconId = this.profileIconId,
        revisionDate = this.revisionDate.toInstant(TimeZone.UTC).toEpochMilliseconds()
    )
}
