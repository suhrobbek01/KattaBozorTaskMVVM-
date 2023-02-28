package com.example.kattabozortaskwithmvvm.models

import java.io.Serializable

data class Offer(
    val attributes: List<Attribute>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: Image,
    val merchant: String,
    val name: String
) : Serializable