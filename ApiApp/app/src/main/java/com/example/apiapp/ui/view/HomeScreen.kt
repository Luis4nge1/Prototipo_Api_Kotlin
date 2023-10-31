package com.example.apiapp.ui.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.apiapp.data.model.Product
import com.example.apiapp.data.model.Producto
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.ui.viewmodel.DataViewModel
import com.example.apiapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    //val state = viewModel.state
    //val homeViewModel : HomeViewModel by viewModels()
    //val homeViewModel1 = ViewModelProvider(context).get(HomeViewModel::class.java)
    val homeViewModel: HomeViewModel = viewModel()

    val productoName: String by homeViewModel.productName
    val productoPrecio: String by homeViewModel.productPrice
    val productoId: String? by homeViewModel.productId
    val isValidButton: Boolean by homeViewModel.isButtonAdd
    val coroutineScope = rememberCoroutineScope()

    val listadeProductos: Producto by homeViewModel.productoResult.observeAsState(initial = Producto())

    println("probando")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Mis Productos", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        TextField(
            value = productoName,
            onValueChange = { homeViewModel.onButtonAddChanged(it, productoPrecio) },
            placeholder = { Text(text = "Nombre del producto") }
        )
        TextField(
            value = productoPrecio,
            onValueChange = { homeViewModel.onButtonAddChanged(productoName,it) },
            placeholder = { Text(text = "Precio") }
        )

        ButtonAdd(isValidButton, homeViewModel)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(listadeProductos) {
                ProductItem(product = it, modifier = Modifier.fillMaxWidth(),
                    onEdit = {
                    //viewModel.editProduct(it)
                        homeViewModel.editProduct(it)
                        showToastInformation(context,"Editando producto "+it)
                },
                    onDelete = {
                    //viewModel.deleteProduct(it)
                        homeViewModel.deleteProduct(it)
                        showToastInformation(context,"Borrando producto "+it)
                })
            }
        }
    }
}

@Composable
fun ButtonAdd(buttonEnabled: Boolean, homeViewModel: HomeViewModel){
    val context = LocalContext.current

    Button(onClick = {
        /*viewModel.createProduct()*/
        homeViewModel.createProduct()
        showToastInformation(context,"Agregando producto")
    },
        enabled = buttonEnabled
    ) {
        Text(text = "Agregar Producto")
    }
}

@Composable
fun ProductItem(
    product: Product,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${product.name} $${product.price}")
        IconButton(onClick = onEdit) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

fun showToastInformation(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}