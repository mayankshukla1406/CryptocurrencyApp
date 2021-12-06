package com.example.cryptocurrencyapp.domain.model

data class Coin(
    val id : String,
    val name : String,
    val symbol : String,
    val isActive : Boolean,
    val rank : Int
)
