package com.example.kattabozortaskwithmvvm.utils

import com.example.kattabozortaskwithmvvm.models.Offer
import com.example.kattabozortaskwithmvvm.models.OffersObject

sealed class OffersResource {
    object Loading : OffersResource()

    class Succes(val offersObject: List<Offer>?) : OffersResource()

    class Error(val message: String) : OffersResource()
}