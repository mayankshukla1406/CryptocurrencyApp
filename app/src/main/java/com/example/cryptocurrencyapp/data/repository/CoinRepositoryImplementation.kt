package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.remote.CryptoCoinApi
import com.example.cryptocurrencyapp.data.remote.dto.CoinDTO
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailDTO
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(
    private val api : CryptoCoinApi
) :CoinRepository{
    override suspend fun getCoins(): List<CoinDTO> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
        return api.getCoinById(coinId = coinId)
    }
}