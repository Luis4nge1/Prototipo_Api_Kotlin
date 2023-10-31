package com.example.apiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.data.repository.DataRepository
import com.example.apiapp.domain.DataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel () : ViewModel() {

    val obtenerDatosUseCase = DataUseCase()

    private val _remoteResult = MutableLiveData<RemoteResult>()
    val remoteResult: LiveData<RemoteResult> = _remoteResult

    private val _productoResult = MutableLiveData<Producto>()
    val productoResult: LiveData<Producto> = _productoResult

    init{
        startData()
    }

    private fun startData(){//(Dispatchers.IO) para viewModelScope
        /*viewModelScope.launch(Dispatchers.IO) {
            obtenerDatosUseCase.getMovies().onSuccess {
                _remoteResult.postValue(it)
            }.onFailure {
                println("Hubo un error")//se puede agregar un popup de error y un boton de restart (lo que quieran)
            }
        }*/
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getProducto().onSuccess {
                _productoResult.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }

}