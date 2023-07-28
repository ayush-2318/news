package com.example.news

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://newsapi.org/v2/everything?q=tesla&from=2023-04-22&sortBy=publishedAt
    //https://newsapi.org/v2/top-headlines?country=in&apiKey

const val BASE_URL="https://newsapi.org/"
const val api_key="f8847a9625b740fd9685f0d1080e039e"

interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$api_key")
    fun getHeadlines(@Query("country")country:String,@Query("page")page:Int):Call<News>

}
object NewsService{
    val newsinstances:NewsInterface
    init{
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsinstances=retrofit.create(NewsInterface::class.java)
    }
}

