package com.example.acase.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("news")
	val news: List<NewsItem?>? = null
)

data class NewsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("picUrl")
	val picUrl: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
