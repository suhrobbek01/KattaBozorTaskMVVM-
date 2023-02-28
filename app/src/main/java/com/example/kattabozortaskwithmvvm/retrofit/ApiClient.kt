package com.example.kattabozortaskwithmvvm.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    const val BASE_URL = "https://www.kattabozor.uz/hh/test/api/"

    var gson = GsonBuilder()
        .setLenient()
        .create()
    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService = getRetrofit().create(ApiService::class.java)
}