package com.example.cryptocurrencyapp.presentation

sealed class Screens(val route:String){
    object CoinListScreen:Screens("coinList_screen")
    object CoinDetailScreen:Screens("coinDetail_screen")
}
