package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "test", fontSize = 15.sp, color = Color.Black, modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
    }
}