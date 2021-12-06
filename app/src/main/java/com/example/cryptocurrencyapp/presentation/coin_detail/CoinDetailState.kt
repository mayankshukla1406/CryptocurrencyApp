package com.example.cryptocurrencyapp.presentation.coin_detail

import com.example.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val error : String = "",
    val coin : CoinDetail? = null
)
