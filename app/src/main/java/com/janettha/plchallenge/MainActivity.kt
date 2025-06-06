package com.janettha.plchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.janettha.plchallenge.ui.theme.PLChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PLChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(modifier = Modifier.padding(innerPadding),
                        text = "")
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
            Text(modifier = Modifier.padding(innerPadding),
                text = "hola")
        }
    }
}
