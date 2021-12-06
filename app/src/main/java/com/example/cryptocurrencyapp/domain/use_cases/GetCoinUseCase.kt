package com.example.cryptocurrencyapp.domain.use_cases

import com.example.cryptocurrencyapp.common.State
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapp.domain.model.CoinDetail
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository : CoinRepository
) {
    operator fun invoke(coinId:String): Flow<State<CoinDetail>> = flow{
        try{
            emit(State.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(State.Success<CoinDetail>(coin))
        }
        catch (e:HttpException){
            emit(State.Error<CoinDetail>(e.localizedMessage?:"An Unexpected Error"))
        }
        catch (e:IOException){
            emit(State.Error<CoinDetail>("Internet Error"))
        }
    }
}