package com.example.acase.data.remote.model

import com.google.gson.annotations.SerializedName

data class MatchesResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("displayRound")
	val displayRound: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("groups")
	val groups: List<Any?>? = null,

	@field:SerializedName("matches")
	val matches: List<MatchesItem?>? = null
)

data class TeamB(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tla_name")
	val tlaName: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null
)

data class MatchesItem(

	@field:SerializedName("team_A")
	val teamA: TeamA? = null,

	@field:SerializedName("date_time_utc")
	val dateTimeUtc: String? = null,

	@field:SerializedName("fts_A")
	val ftsA: Int? = null,

	@field:SerializedName("hts_A")
	val htsA: Int? = null,

	@field:SerializedName("extras")
	val extras: Extras? = null,

	@field:SerializedName("match_time")
	val matchTime: String? = null,

	@field:SerializedName("hts_B")
	val htsB: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fts_B")
	val ftsB: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("team_B")
	val teamB: TeamB? = null
)

data class TeamA(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tla_name")
	val tlaName: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null
)

data class Extras(

	@field:SerializedName("iddaa_live")
	val iddaaLive: Boolean? = null,

	@field:SerializedName("iddaa_code")
	val iddaaCode: Int? = null,

	@field:SerializedName("team_A_redcards")
	val teamARedcards: Int? = null,

	@field:SerializedName("team_B_redcards")
	val teamBRedcards: Int? = null
)
