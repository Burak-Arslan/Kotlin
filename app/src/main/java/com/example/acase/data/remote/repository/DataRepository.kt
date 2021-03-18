package com.example.acase.data.remote.repository


import com.example.acase.data.remote.model.MatchesResponse
import com.example.acase.data.remote.model.NewsResponse
import com.example.acase.data.remote.service.Api
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(val apiService: Api) {

    public suspend fun getNews(): Response<NewsResponse> {
        return apiService.getNews()
    }

    public suspend fun getMatches(): Response<MatchesResponse> {
        return apiService.getMatches()
    }
}