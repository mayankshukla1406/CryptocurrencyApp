package com.example.cryptocurrencyapp.presentation.coin_list

import com.example.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coinsList : List<Coin> = emptyList(),
    val error : String = ""
)
