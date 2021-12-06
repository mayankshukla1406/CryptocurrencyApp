package com.example.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.domain.use_cases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val stateDetail = mutableStateOf(CoinDetailState())
    val stateCoinDetail : State<CoinDetailState> = stateDetail

    init {
        savedStateHandle.get<String>("coinId")?.let{coinId->
            getCoin(coinId)
        }
    }
    private fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach {
            when(it){
                is com.example.cryptocurrencyapp.common.State.Success->{
                    stateDetail.value = CoinDetailState(coin=it.data)
                }
                is com.example.cryptocurrencyapp.common.State.Loading->{
                    stateDetail.value = CoinDetailState(isLoading = true)
                }
                is com.example.cryptocurrencyapp.common.State.Error->{
                    stateDetail.value = CoinDetailState(error = it.message?:"An Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}