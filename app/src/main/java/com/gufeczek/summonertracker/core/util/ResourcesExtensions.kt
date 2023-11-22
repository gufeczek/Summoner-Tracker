package com.gufeczek.summonertracker.core.util

import androidx.annotation.DimenRes
import com.gufeczek.summonertracker.SummonerTrackerApp

fun getDimensionInDp(@DimenRes dimen: Int): Float? {
    val context = SummonerTrackerApp.getCurrentActivity() ?: return null
    return context.resources.getDimension(dimen).toInt() / context.resources.displayMetrics.density
}
