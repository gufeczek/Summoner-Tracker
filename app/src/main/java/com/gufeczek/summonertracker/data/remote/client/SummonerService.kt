package com.gufeczek.summonertracker.data.remote.client

import com.gufeczek.summonertracker.core.network.NetworkResult
import com.gufeczek.summonertracker.data.remote.response.SummonerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SummonerService {
    @GET("summoner/{summonerName}")
    suspend fun getSummoner(
        @Query("summonerName") summonerName: String
    ): NetworkResult<SummonerResponse>
}
