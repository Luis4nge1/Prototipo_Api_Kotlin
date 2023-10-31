package com.example.apiapp.network

import com.example.apiapp.data.model.Product
import com.example.apiapp.data.model.ProductDto
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//https://crudcrud.com/api/b599b124a5cb4952bad2820f33d7b419
//https://crudcrud.com/api/eccff00b25a34f8286f02eb171e5288d
interface ApiService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listDataApiService(
        @Query("api_key") apiKey:String,
        @Query("region") region:String
    ): RemoteResult //Response<RemoteResult>

    @GET("/api/b599b124a5cb4952bad2820f33d7b419/products")
    suspend fun listProductoApiService( ): Producto

    @POST("/api/b599b124a5cb4952bad2820f33d7b419/products")
    suspend fun insertProduct(@Body product: ProductDto): Product

    @PUT("/api/b599b124a5cb4952bad2820f33d7b419/products/{id}")
    suspend fun updateProduct(@Body product: ProductDto, @Path("id") id: String)

    @DELETE("/api/b599b124a5cb4952bad2820f33d7b419/products/{id}")
    suspend fun deleteProduct(@Path("id") id: String)
}