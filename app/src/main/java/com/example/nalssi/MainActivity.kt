package com.example.nalssi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nalssi.core.theme.NalssiTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NalssiTheme {
                KoinContext {
                    MainScreen()
                }
            }
        }
    }
}
