package com.example.kattabozortaskwithmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kattabozortaskwithmvvm.repository.OfferRepository
import com.example.kattabozortaskwithmvvm.utils.NetworkHelper

class ViewModelFactory(
    private val offerRepository: OfferRepository,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferViewModel::class.java)) {
            return OfferViewModel(offerRepository, networkHelper) as T
        }
        throw Exception("Error")
    }

}