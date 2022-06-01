package com.ihsan.quick_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ihsan.quick_news.R
import com.ihsan.quick_news.data.dto.News

class ListNewsAdapter(private val listNews: ArrayList<News>) :
    RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        var tvTime: TextView = itemView.findViewById(R.id.tv_item_time)
        var imgCover: ImageView = itemView.findViewById(R.id.img_cover)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListNewsAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListNewsAdapter.ListViewHolder, position: Int) {
        val news = listNews[position]
        Glide.with(holder.itemView.context)
            .load(news.cover)
            .into(holder.imgCover)
        holder.tvTitle.text = news.title
        holder.tvDate.text = news.date
        holder.tvTime.text = news.time
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNews[position]) }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }
}