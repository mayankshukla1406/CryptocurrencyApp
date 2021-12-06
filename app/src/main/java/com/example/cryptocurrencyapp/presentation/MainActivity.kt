package com.example.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.example.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.example.cryptocurrencyapp.presentation.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.assertThreadDoesntHoldLock

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screens.CoinListScreen.route
                    ){
                        composable(route=Screens.CoinListScreen.route){
                            CoinListScreen(navController = navController)
                        }
                        composable(route=Screens.CoinDetailScreen.route+"/{coinId}"){
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}