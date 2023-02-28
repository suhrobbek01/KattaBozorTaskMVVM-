package com.example.kattabozortaskwithmvvm.retrofit

import com.example.kattabozortaskwithmvvm.models.OffersObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/offers")
    suspend fun getOffers(): Response<OffersObject>
}