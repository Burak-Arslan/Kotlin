package com.example.acase.ui.scores.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acase.R
import com.example.acase.data.remote.model.MatchesResponse
import kotlinx.android.synthetic.main.recycler_scores_item.view.*


class ScoresRcyclerAdapter(
    private val scoresList: MatchesResponse?,
) :
    RecyclerView.Adapter<ScoresRcyclerAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_scores_item, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return scoresList!!.matches!!.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        try {
            holder.itemView.txtTeamHome.text = scoresList?.matches!![position]?.teamA!!.name
            holder.itemView.txtTeamHomeScore.text = scoresList?.matches!![position]?.ftsA.toString() +" - "
            holder.itemView.txtTeamDisplacementScore.text = scoresList?.matches!![position]?.ftsB.toString()
            holder.itemView.txtTeamDisplacement.text = scoresList?.matches!![position]?.teamB!!.name

        } catch (e: Exception) {
            e.message?.let { Log.e("ScoresonBindViewHolder", it) }
        }
    }

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}