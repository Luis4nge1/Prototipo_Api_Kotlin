package com.example.apiapp.data.repository

import com.example.apiapp.data.model.Product
import com.example.apiapp.data.model.ProductDto
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.network.ApiInstance

class DataRepository {

    private val service = ApiInstance.apiInstance

    suspend fun getDataRepository() : Result<RemoteResult>{
        return try {
            val response = service.listDataApiService("939a2fbf4c80f34304b978407720ff6e","US")
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
            //RemoteResult(0, emptyList(),0,0) //devuelve vacio
        }
    }

    suspend fun getProductoRepository() : Result<Producto>{
        return try {
            val response = service.listProductoApiService()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
            //RemoteResult(0, emptyList(),0,0) //devuelve vacio
        }
    }

    suspend fun insertRepository(productDto :ProductDto): Result<Product> {
        return try {
            val response = service.insertProduct(productDto)
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
            //RemoteResult(0, emptyList(),0,0) //devuelve vacio
        }

    }

    suspend fun updateProductoRepository(productDto :ProductDto, idProducto: String) {
        service.updateProduct(productDto, idProducto)
    }

    suspend fun deleteProductoRepository(idData :String) {
        service.deleteProduct(id = idData)
    }

}