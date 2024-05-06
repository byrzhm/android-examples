package com.example.buptclock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.example.buptclock.ui.theme.AnalogClockBoxColor
import com.example.buptclock.ui.theme.AnalogClockHourHandColor
import com.example.buptclock.ui.theme.AnalogClockMinuteHandColor
import com.example.buptclock.ui.theme.AnalogClockSecondHandColor
import kotlinx.coroutines.delay
import java.util.Calendar
import kotlin.math.min

@Composable
fun BuptAnalogClock(
    modifier: Modifier = Modifier,
) {
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }
    var second by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            val calendar = Calendar.getInstance()
            hour = calendar.get(Calendar.HOUR) // 0-11
            minute = calendar.get(Calendar.MINUTE)
            second = calendar.get(Calendar.SECOND)

            delay(1000)
        }
    }

    BuptAnalogClock(
        hour = hour,
        minute = minute,
        second = second,
        modifier = modifier
    )
}

@Composable
fun BuptAnalogClock(
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "BUPT",
            style = MaterialTheme.typography.titleLarge,
        )
        Box(
            modifier = Modifier
                .fillMaxSize(fraction = 0.6f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(AnalogClockBoxColor)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val diameter = min(size.width, size.height) * 0.9f
                val radius = diameter / 2


                repeat(12) {
                    val start = center - Offset(0f, radius)
                    val end = start + Offset(0f, radius / 40f)
                    rotate(it / 12f * 360, center) {
                        drawLine(
                            color = Color.Black,
                            start = start,
                            end = end,
                            strokeWidth = 5.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }

                val hourRatio = hour / 12f + minute / 720f + second / 43200f
                val minuteRatio = minute / 60f + second / 3600f
                val secondRatio = second / 60f

                rotate(hourRatio * 360, center) {
                    drawLine(
                        color = AnalogClockHourHandColor,
                        start = center - Offset(0f, radius * 0.4f),
                        end = center + Offset(0f, radius * 0.1f),
                        strokeWidth = 3.8.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                rotate(minuteRatio * 360, center) {
                    drawLine(
                        color = AnalogClockMinuteHandColor,
                        start = center - Offset(0f, radius * 0.6f),
                        end = center + Offset(0f, radius * 0.1f),
                        strokeWidth = 2.8.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                rotate(secondRatio * 360, center) {
                    drawLine(
                        color = AnalogClockSecondHandColor,
                        start = center - Offset(0f, radius * 0.8f),
                        end = center + Offset(0f, radius * 0.1f),
                        strokeWidth = 1.8.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                // center dot
                drawCircle(
                    color = Color.Black,
                    radius = 5.dp.toPx(),
                    center = center
                )
            }
        }

    }
}
