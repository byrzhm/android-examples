package com.example.buptclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.buptclock.ui.theme.BuptClockTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuptClockTheme {
                BuptClockApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun BuptClockApp(
    modifier: Modifier = Modifier
) {
    val pageState = rememberPagerState(pageCount = {
        2
    })

    HorizontalPager(state = pageState) { page ->
        when (page) {
            0 -> AnalogClockPage(modifier = modifier)
            1 -> TextClockPage(modifier = modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun BuptClockAppPreview(
    modifier: Modifier = Modifier
) {
    BuptClockTheme {
        BuptClockApp(modifier = modifier.fillMaxSize())
    }
}
