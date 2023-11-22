package com.gufeczek.summonertracker.core.ui.leaderboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gufeczek.summonertracker.R
import com.gufeczek.summonertracker.data.model.Summoner
import com.gufeczek.summonertracker.databinding.ItemSummonerBinding

class LeaderboardAdapter(private val items: List<Summoner>) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSummonerBinding.inflate(inflater)
        return ViewHolder(binding.root).apply {
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].summonerName
    }
}
