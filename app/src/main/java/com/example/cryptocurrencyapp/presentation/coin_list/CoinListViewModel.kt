package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.domain.use_cases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel(){

    private val state = mutableStateOf(CoinListState())
    val stateData : State<CoinListState> = state

    init {
        getCoinsList()
    }
    private fun getCoinsList(){
        getCoinsUseCase().onEach {coinList->
            when(coinList){
                is
                com.example.cryptocurrencyapp.common.State.Success ->{
                    state.value = CoinListState(
                        coinsList = coinList.data?: emptyList()
                    )
                }
                is com.example.cryptocurrencyapp.common.State.Error->{
                   state.value = CoinListState(
                       error = coinList.message?:"An Unexpected Error"
                   )
                }
                is com.example.cryptocurrencyapp.common.State.Loading->{
                    state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}