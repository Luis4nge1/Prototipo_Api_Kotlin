package com.example.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.apiapp.data.model.RemoteResult
import com.example.apiapp.ui.theme.ApiAppTheme
import com.example.apiapp.ui.view.HomeScreen
import com.example.apiapp.ui.viewmodel.DataViewModel

class MainActivity : ComponentActivity() {

    private val dataViewModel : DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android", dataViewModel)
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, dataViewModel: DataViewModel, modifier: Modifier = Modifier) {

    val resultado: RemoteResult by dataViewModel.remoteResult.observeAsState(initial = RemoteResult(0,
        emptyList(),0,0)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1){
            resultado.results.forEach {
                Text(text = it.title, textAlign = TextAlign.Center)
            }

        }

    }

    /*Text(
        text = "Hello $name!",
        modifier = modifier
    )*/
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiAppTheme {
        //Greeting("Android")
    }
}