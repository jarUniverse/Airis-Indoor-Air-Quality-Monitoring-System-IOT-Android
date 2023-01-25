package com.rizkifajar.airis

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ThingspeakService {
    @GET("channels/{channel}/feeds.json")
    fun getFeeds(@Path("channel") channel: Int, @Query("api_key") apikey: String): Call<Feeds>
}

class Feeds{
    @SerializedName("feeds")
    val feeds: List<Feed>? = null
}

class Feed {
    @SerializedName("field1")
    val field1: String? = null
    @SerializedName("field2")
    val field2: String? = null
    @SerializedName("field3")
    val field3: String? = null
    @SerializedName("field4")
    val field4: String? = null
    @SerializedName("field5")
    val field5: String? = null
    @SerializedName("field6")
    val field6: String? = null
    @SerializedName("field7")
    val field7: String? = null
    @SerializedName("field8")
    val field8: String? = null
}