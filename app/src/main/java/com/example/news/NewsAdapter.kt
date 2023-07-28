package com.example.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent as Intent

class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       val view=LayoutInflater.from(context).inflate(R.layout.itemnews_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=articles[position]
        holder.newsTitle.text=article.title
        holder.newsDescription.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            //Toast.makeText("this",article.title,Toast.LENGTH_SHORT).show()
            val intent= Intent(context,Detail::class.java)
            intent.putExtra("URL",article.url)
           context. startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.ivnewsimg)
        var newsTitle = itemView.findViewById<TextView>(R.id.tvtitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.tvdes)
    }
}