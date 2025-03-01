package com.example.backgroundtask

import android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.backgroundtask.broadcast.BroadcastNotification
import com.example.backgroundtask.ui.theme.BackgroundTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val intentService = Intent(this, ServiceClass::class.java)
//        startService(intentService)

//        val broadcastIntent = IntentFilter(ACTION_AIRPLANE_MODE_CHANGED)
//        registerReceiver(BroadcastNotification(), broadcastIntent)

        enableEdgeToEdge()
        setContent {
            BackgroundTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WorkMangerCookie()
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BackgroundTaskTheme {
        Greeting("Android")
    }
}