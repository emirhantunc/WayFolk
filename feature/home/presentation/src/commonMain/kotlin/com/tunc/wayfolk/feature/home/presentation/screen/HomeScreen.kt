package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants


@Composable
fun HomeScreen() {

    BaseScreen(
        screenName = HomeConstants.Home.NAME,
        viewModel = HomeViewModel(),
        content = {
            Text(
                text = "test", fontSize = 15.sp, color = Color.Black, modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )
        }
    )
}