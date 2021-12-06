package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencyapp.presentation.Screens
import com.example.cryptocurrencyapp.presentation.coin_list.component.CoinItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
){
    val stateValue = viewModel.stateData.value
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(stateValue.coinsList){coin->
                CoinItem(coin = coin, onItemClick = {
                    navController.navigate(Screens.CoinDetailScreen.route+"/${coin.id}")
                })
            }
        }
    }
    if(stateValue.error.isNotBlank()){
        Text(
           text=stateValue.error,color=MaterialTheme.colors.error,
           textAlign = TextAlign.Center,
           modifier= Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
        )
    }
    if(stateValue.isLoading){
        CircularProgressIndicator()
    }
}