package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.remote.dto.CoinDTO
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailDTO

interface CoinRepository {

    suspend fun getCoins() : List<CoinDTO>
    suspend fun getCoinById(coinId:String):CoinDetailDTO
}