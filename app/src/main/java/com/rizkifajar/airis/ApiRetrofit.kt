package com.rizkifajar.airis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {
    val endPoint: ThingspeakService
        get(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.thingspeak.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val thingspeakService =  retrofit.create(ThingspeakService::class.java)
//            val feeds = thingspeakService.getFeeds(1768210, "6VWSZ3P4L9SIY09V")
            return thingspeakService
        }
}