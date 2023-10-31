package com.example.apiapp.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)