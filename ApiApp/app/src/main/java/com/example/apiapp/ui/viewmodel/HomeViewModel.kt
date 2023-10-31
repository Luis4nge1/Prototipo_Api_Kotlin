package com.example.apiapp.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapp.data.model.Product
import com.example.apiapp.data.model.ProductDto
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.domain.DataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _productName = mutableStateOf("")
    val productName: State<String> = _productName

    private val _productPrice = mutableStateOf("")
    val productPrice: State<String> = _productPrice

    private val _productId = mutableStateOf<String?>(null)
    val productId: State<String?> = _productId

    private val _isButtonAdd = mutableStateOf(false)
    val isButtonAdd: State<Boolean> = _isButtonAdd

    /*var state by mutableStateOf(HomeState())
        private set

    private val _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> = _homeState*/

    val obtenerDatosUseCase = DataUseCase()

    private val _remoteResult = MutableLiveData<RemoteResult>()
    val remoteResult: LiveData<RemoteResult> = _remoteResult

    private val _productoResult = MutableLiveData<Producto>()
    val productoResult: LiveData<Producto> = _productoResult

    init{
        getProductos()
    }

    private fun getProductos(){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getProducto().onSuccess {
                _productoResult.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }

    /*init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            println("LLega")
            println("LLegandoooooo $state")
            println("LLegadita $productService")
            try {

                val products = productService.getProducts()
                println("verifica: "+products)
                state = state.copy(
                    products = products
                )
            } catch (e: Exception) {
                println("ERROR")
            }
        }
    }*/



    fun changeName(name: String) {
        _productName.value = name
        println("VIENDO NOMBRE PRODUCTO: "+name)
        /*state = state.copy(
            productName = name
        )*/
    }

    fun changePrice(price: String) {
        _productPrice.value = price
        println("VIENDO PRECIO: "+price)
        /*state = state.copy(
            productPrice = price
        )*/
    }

    fun onButtonAddChanged(name: String, price: String) {
        changeName(name)
        changePrice(price)
        _isButtonAdd.value = isValidPrice(price) && isValidName(name)
    }

    private fun isValidName(name: String): Boolean = name.isNotEmpty()

    private fun isValidPrice(price: String): Boolean  = price.isNotEmpty()

    fun editProduct(product: Product) {
        _productName.value = product.name
        _productPrice.value = product.price.toString()
        _productId.value = product.id
        println("DATOS AGREGADOS "+_productId.toString())
    }

   fun deleteProduct(product: Product) {
        viewModelScope.launch {
            try {
                obtenerDatosUseCase.deleteProducto(product.id)
                println("BORRANDO AL PRODUCTO "+product)
            } catch (e: Exception) {
                println("HUBO UN ERROR NO SE PUDO BORRAR")
            }
            getProductos()
        }
    }

    fun createProduct() {
        val productoDTO: ProductDto =
            ProductDto(
                name = _productName.value,
                price = _productPrice.value.toDouble()
            )
        viewModelScope.launch {
            try {
                if (_productId.value == null) {
                    insertProduct(productoDTO)
                } else {
                    updateProduct(productoDTO, _productId.value.toString())
                }
            } catch (e: Exception) {
                println()
            }
            getProductos()
        }
        _productName.value = ""
        _productPrice.value = ""
        _productId.value = null
        _isButtonAdd.value = false
    }

    suspend fun insertProduct(productoDTO : ProductDto){
        obtenerDatosUseCase.insertProducto(productoDTO).onSuccess {
            println("PRODUCTO AGREGADO CORRECTAMENTE")
        }.onFailure {
            println("HUBO UN ERROR AL AGREGAR EL PRODUCTO")
        }
    }

    suspend fun  updateProduct(productoDTO : ProductDto, idProducto: String) {
        viewModelScope.launch {
            try {
                obtenerDatosUseCase.updateProducto(productoDTO, idProducto)
                println("ACTUALIZANDO AL PRODUCTO "+idProducto)
            } catch (e: Exception) {
                println("HUBO UN ERROR NO SE PUDO ACTUALIZAR")
            }
            getProductos()
        }
    }

}