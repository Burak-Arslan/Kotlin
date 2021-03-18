package com.example.acase.data.remote.service

import com.example.acase.data.remote.model.MatchesResponse
import com.example.acase.data.remote.model.NewsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @Headers("Content-Type: application/json")
    @GET("news")
    suspend fun getNews(): Response<NewsResponse>


    @Headers("Content-Type: application/json")
    @GET("matches")
    suspend fun getMatches(): Response<MatchesResponse>
}