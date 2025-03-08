package com.example.unituitesting

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterApp(modifier: Modifier) {
    var count by remember { mutableStateOf(0) }
    Column {
        Spacer(Modifier.height(100.dp))
        Button(onClick = {
            count ++
        },
            Modifier.padding(32.dp)) {
            Text("Count is $count")
        }
    }

}











