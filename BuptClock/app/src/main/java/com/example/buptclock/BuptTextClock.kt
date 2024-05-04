package com.example.buptclock

import android.widget.TextClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun BuptTextClock(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "BUPT",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 24.dp)
        )
        AndroidView(
            factory = { context ->
                TextClock(context).apply {
                    format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                    timeZone?.let { this.timeZone = it }
                    textSize.let { this.textSize = 30f }
                }
            },
            modifier = modifier
        )
    }
}
