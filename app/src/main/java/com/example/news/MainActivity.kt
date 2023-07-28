package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles= mutableListOf<Article>()
    var pagenumber=1
    var totalres=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter= NewsAdapter(this@MainActivity,articles)
        newslist.adapter=adapter
        newslist.layoutManager=LinearLayoutManager(this@MainActivity)
        getnews()

    }

    private fun getnews() {
        val news=NewsService.newsinstances.getHeadlines("in",pagenumber)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news=response.body()
                if(news!=null)
                {
                    totalres=news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    Log.d("ayush","acess")

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("ayush","error in fetching",t)
            }

        })
    }
}