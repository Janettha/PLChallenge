package com.janettha.plchallenge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.janettha.plchallenge.batteryIndicatorUI.BatteryIndicatorUI2
import com.janettha.plchallenge.ui.theme.PLChallengeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PLChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BatteryIndicatorUI2(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThousandsSeparatorPickerPreview() {
    PLChallengeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            BatteryIndicatorUI2(modifier = Modifier.padding(innerPadding))
        }
    }
}
