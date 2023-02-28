package com.example.kattabozortaskwithmvvm.repository

import com.example.kattabozortaskwithmvvm.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class OfferRepository(private val apiService: ApiService) {
    suspend fun getOffers() = flow { emit(apiService.getOffers()) }
}