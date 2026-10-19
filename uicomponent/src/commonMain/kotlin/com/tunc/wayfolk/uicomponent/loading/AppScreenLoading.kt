package com.tunc.wayfolk.uicomponent.loading

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppScreenLoading(
    modifier: Modifier = Modifier,
    show: Boolean,
    contentPadding: PaddingValues = PaddingValues(vertical = 4.dp)
) {
    val loadingModifier = remember(contentPadding) {
        Modifier
            .fillMaxWidth()
            .padding(contentPadding)
            .height(2.dp)
    }

    if (show) {
        Text(
            text = "Loading...",
            modifier = modifier.then(loadingModifier)
        ) // will replace with real loading content
    } else {
        Spacer(modifier = modifier.then(loadingModifier))
    }
}