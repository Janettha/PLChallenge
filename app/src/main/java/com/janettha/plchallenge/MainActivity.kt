package com.janettha.plchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.janettha.plchallenge.ThousandsSeparatorPicker.presenter.ThousandsSeparatorPicker
import com.janettha.plchallenge.ui.theme.PLChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThousandsSeparatorPicker(
                        modifier = Modifier
                            .padding(innerPadding),
                    )
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
            ThousandsSeparatorPicker(modifier = Modifier.padding(innerPadding))
        }
    }
}
