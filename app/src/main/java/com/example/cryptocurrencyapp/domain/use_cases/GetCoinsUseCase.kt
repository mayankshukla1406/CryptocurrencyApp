package com.example.cryptocurrencyapp.domain.use_cases

import com.example.cryptocurrencyapp.common.State
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<State<List<Coin>>> = flow {
        try {
            emit(State.Loading<List<Coin>>())
            val coins = repository.getCoins().map {
                it.toCoin()
            }
            emit(State.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(State.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected Error"))
        } catch (e: IOException) {
            emit(State.Error<List<Coin>>("Internet Error"))
        }
    }
}