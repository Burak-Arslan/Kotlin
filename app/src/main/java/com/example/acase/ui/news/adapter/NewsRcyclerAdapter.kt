package com.example.acase.ui.news.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.acase.R
import com.example.acase.data.remote.model.NewsItem
import com.example.acase.data.remote.model.NewsResponse
import kotlinx.android.synthetic.main.recycler_news_item.view.*


class NewsRcyclerAdapter(
    private val newsList: NewsResponse?,
    private val listener: OnItemClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<NewsRcyclerAdapter.NotificationViewHolder>() {

    private lateinit var currentItem: NewsResponse
    private var selectedPos = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_news_item, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList?.news?.size!!
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        try {

            Glide
                .with(context)
                .load(newsList?.news?.get(position)?.picUrl)
                .into(holder.itemView.imgNews)

            holder.itemView.txtNewsTitle.text = newsList?.news?.get(position)?.title
            holder.itemView.txtNewsDate.text = newsList?.news?.get(position)?.date

        } catch (e: Exception) {
            e.message?.let { Log.e("NewsonBindViewHolder", it) }
        }
    }

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedPos = getLayoutPosition();
            listener.onItemClick( newsList?.news?.get(selectedPos))
        }
    }

    interface OnItemClickListener {
        fun onItemClick(currentItem: NewsItem?)
    }
}