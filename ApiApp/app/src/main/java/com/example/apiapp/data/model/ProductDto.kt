package com.example.apiapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)