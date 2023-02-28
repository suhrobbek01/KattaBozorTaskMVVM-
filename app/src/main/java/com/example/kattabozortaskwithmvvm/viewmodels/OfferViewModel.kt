package com.example.kattabozortaskwithmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kattabozortaskwithmvvm.repository.OfferRepository
import com.example.kattabozortaskwithmvvm.utils.NetworkHelper
import com.example.kattabozortaskwithmvvm.utils.OffersResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OfferViewModel(
    private val offerRepository: OfferRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun fetchOffers(): StateFlow<OffersResource> {
        val stateFlow = MutableStateFlow<OffersResource>(OffersResource.Loading)

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                offerRepository.getOffers().catch {
                    stateFlow.emit(OffersResource.Error(it.message ?: ""))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(OffersResource.Succes(it.body()?.offers))
                    } else {
                        stateFlow.emit(OffersResource.Error(it.message()))
                    }
                }
            } else {
                stateFlow.emit(OffersResource.Error("No internet connection"))
            }
        }
        return stateFlow
    }
}