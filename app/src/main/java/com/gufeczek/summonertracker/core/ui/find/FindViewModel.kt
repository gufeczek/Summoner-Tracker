package com.gufeczek.summonertracker.core.ui.find

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gufeczek.summonertracker.core.util.preference.PreferenceConstants.SELECTED_REGION
import com.gufeczek.summonertracker.core.util.preference.PreferenceHelper
import com.gufeczek.summonertracker.data.model.Summoner
import com.gufeczek.summonertracker.data.repository.SummonerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val repository: SummonerRepository,
    private val helper: PreferenceHelper
) : ViewModel() {
    private val _summoner = MutableStateFlow<Summoner?>(null)
    val summoner = _summoner.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun onSearch(summonerName: String) = viewModelScope.launch {
        val summoner = repository.getSummoner(summonerName = summonerName)
        Log.d(TAG, "summoner icon id: ${summoner?.profileIconId}")
    }

    fun getSelectedRegion(): String = runBlocking {
        helper.getPreference(SELECTED_REGION) ?: "EU"
    }

    fun putSelectedRegion(region: String) {
        viewModelScope.launch {
            helper.putPreference(SELECTED_REGION, region)
        }
    }

    companion object {
        const val TAG = "FindViewModel"
    }
}
