package com.example.simpleenergytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleenergytask.ui.theme.SimpleEnergyTaskTheme
import kotlinx.coroutines.delay
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                                top = paddingValues.calculateTopPadding(),
                                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                                bottom = paddingValues.calculateBottomPadding()
                            )
                    ) {
                        SpeedOdoTripScreen()
                    }
                })
        }
    }
}

@Composable
fun SpeedOdoTripScreen() {
    var speed by remember { mutableIntStateOf(0) }
    var odometer by remember { mutableFloatStateOf(0.0f) }
    var trip by remember { mutableFloatStateOf(0.0f) }

    LaunchedEffect(Unit) {
        // Simulate dynamic updates
        while (true) {
            delay(1000) // Update every second
            speed = (10..120).random() // Random speed for testing
            odometer += 0.1f // Increment odometer
            trip += 0.05f // Increment trip
        }
    }

    Greeting(speed = speed, odometer = odometer, trip = trip) {
        trip= 0F
    }
}

@Composable
fun Greeting(speed: Int, odometer: Float, trip: Float, tripReset: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.green)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "MOTOR ON",
                modifier = Modifier,
                fontSize = 46.sp,
                fontWeight = Bold,
                color = Color.White
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "$speed", fontSize = 96.sp, fontWeight = Bold, color = Color.White)
                Text(text = "km/hr", fontSize = 26.sp, color = Color.White)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    IconButton(onClick = { tripReset() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trip_reset),
                            contentDescription = ""
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Trip A", color = Color.White, fontSize = 26.sp)
                        Text(
                            text = String.format(Locale.getDefault(), "%.2f", trip),
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play_arrow),
                        contentDescription = "g",
                        Modifier
                            .rotate(180f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "E", color = Color.Gray, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "R", color = Color.Gray, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "D", color = Color.Gray, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "SONIC", fontWeight = Bold, color = Color.Red, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play_arrow),
                        contentDescription = "g",
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ODO", color = Color.White, fontSize = 26.sp)
                    Text(
                        text = String.format(Locale.getDefault(), "%.2f", odometer),
                        color = Color.White,
                        fontSize = 26.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:CD Tech")
@Composable
fun GreetingPreview() {
    SimpleEnergyTaskTheme {
        Greeting(10, 20f, 30f){}
    }
}