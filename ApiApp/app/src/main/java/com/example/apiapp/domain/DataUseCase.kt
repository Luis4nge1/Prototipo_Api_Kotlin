package com.example.apiapp.domain

import com.example.apiapp.data.model.Product
import com.example.apiapp.data.model.ProductDto
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.data.repository.DataRepository

class DataUseCase {

    private val repository = DataRepository()

    suspend fun getMovies(): Result<RemoteResult> {
        //return repository.getPopularityMovies()
        return repository.getDataRepository()
            /*.onSuccess { it ->
            it.results.forEach {
                println("Pelicula145 "+it.title)
            }
        }.onFailure {
            println("Hubo un error")//se puede agregar un popup de error y un boton de restart
        }*/
    }

    suspend fun getProducto(): Result<Producto> {
        return repository.getProductoRepository()
    }

    suspend fun insertProducto(productDto: ProductDto) : Result<Product> {
        return repository.insertRepository(productDto)
    }

    suspend fun updateProducto(productDto: ProductDto, idProducto: String) {
        repository.updateProductoRepository(productDto, idProducto)
    }

    suspend fun deleteProducto(idProducto: String) {
        repository.deleteProductoRepository(idProducto)
    }

}