package com.eugo.samplewithcodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eugo.samplewithcodex.ui.theme.SampleWithCodexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleWithCodexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherForecast(
                        forecasts = sampleForecast,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

data class DailyForecast(val day: String, val description: String, val icon: ImageVector)

private val sampleForecast = listOf(
    DailyForecast("今日", "晴れ", Icons.Filled.WbSunny),
    DailyForecast("火", "曇り", Icons.Filled.Cloud),
    DailyForecast("水", "雨", Icons.Filled.Umbrella),
    DailyForecast("木", "雪", Icons.Filled.AcUnit),
    DailyForecast("金", "晴れ", Icons.Filled.WbSunny),
    DailyForecast("土", "曇り", Icons.Filled.Cloud),
    DailyForecast("日", "雷", Icons.Filled.Bolt)
)

@Composable
fun WeatherForecast(forecasts: List<DailyForecast>, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val today = forecasts.first()
        TodayWeather(today)
        forecasts.drop(1).forEach { day ->
            DailyWeather(day)
        }
    }
}

@Composable
fun TodayWeather(forecast: DailyForecast) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 16.dp)) {
        Icon(
            imageVector = forecast.icon,
            contentDescription = forecast.description,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = "${forecast.day}: ${forecast.description}",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun DailyWeather(forecast: DailyForecast) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = forecast.icon,
            contentDescription = forecast.description,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(text = "${forecast.day}: ${forecast.description}")
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherForecastPreview() {
    SampleWithCodexTheme {
        WeatherForecast(forecasts = sampleForecast)
    }
}
