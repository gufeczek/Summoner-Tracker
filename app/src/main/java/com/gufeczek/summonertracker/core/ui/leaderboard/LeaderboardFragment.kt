package com.gufeczek.summonertracker.core.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gufeczek.summonertracker.core.ui.leaderboard.adapter.LeaderboardAdapter
import com.gufeczek.summonertracker.data.model.Summoner
import com.gufeczek.summonertracker.databinding.FragmentLeaderboardBinding
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class LeaderboardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = LeaderboardAdapter(
            listOf(
                Summoner(
                    "1",
                    "JustPotatoThings",
                    1,
                    1,
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                )
            )
        )
    }
}
