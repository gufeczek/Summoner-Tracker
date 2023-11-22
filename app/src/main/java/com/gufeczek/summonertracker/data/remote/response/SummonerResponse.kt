package com.gufeczek.summonertracker.data.remote.response

import com.gufeczek.summonertracker.data.model.Summoner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@JsonClass(generateAdapter = true)
data class SummonerResponse(
    @field:Json(name = "id")
    val id: String,

    @field:Json(name = "summonerName")
    val summonerName: String,

    @field:Json(name = "profileIconId")
    val profileIconId: Int,

    @field:Json(name = "revisionDate")
    val revisionDate: Long,

    @field:Json(name = "summonerLevel")
    val summonerLevel: Int
) {
    fun asDomain() = Summoner(
        id = this.id,
        summonerName = this.summonerName,
        summonerLevel = this.summonerLevel,
        profileIconId = this.profileIconId,
        revisionDate = Instant.fromEpochMilliseconds(this.revisionDate).toLocalDateTime(TimeZone.UTC)
    )
}
