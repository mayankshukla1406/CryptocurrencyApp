package com.example.cryptocurrencyapp.presentation.coin_detail

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencyapp.presentation.coin_detail.component.CoinTag
import com.example.cryptocurrencyapp.presentation.coin_detail.component.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val stateDetail = viewModel.stateCoinDetail.value
    Box(modifier = Modifier.fillMaxSize()) {
        stateDetail.coin?.let {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${it.rank}. ${it.name} (${it.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(7f)
                        )
                        Text(
                            text = if (it.isActive) "Active" else "InActive",
                            color = if (it.isActive) Color.Blue else Color.Black,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterVertically
                            ))
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = it.description, style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        it.tags.forEach{
                            CoinTag(tag = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Team Members", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(it.team){teamMember->
                    TeamListItem(teamMember =teamMember,modifier= Modifier
                        .fillMaxWidth()
                        .padding(10.dp) )
                    Divider()
                }
            }
        }
        if(stateDetail.error.isNotBlank()){
            Text(text = stateDetail.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .align(Alignment.Center)
            )
        }
        if(stateDetail.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}