package com.example.apiapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://api.themoviedb.org/3/
object ApiInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://crudcrud.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstance by lazy{
        retrofit.create(ApiService::class.java)
    }

}