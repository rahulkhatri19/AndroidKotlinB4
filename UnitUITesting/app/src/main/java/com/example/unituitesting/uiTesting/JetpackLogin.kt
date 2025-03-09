package com.example.unituitesting.uiTesting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.unituitesting.Utility.EMAIL
import com.example.unituitesting.Utility.LOGIN
import com.example.unituitesting.Utility.PASSWORD

@Composable
fun JetpackLogin() {

    var inputEmail by remember { mutableStateOf(TextFieldValue("")) }
    var inputPassword by remember { mutableStateOf(TextFieldValue("")) }
    var btnClick by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(Modifier.height(120.dp))

        OutlinedTextField(
            value = inputEmail,
            onValueChange = {
                inputEmail = it
            },
            label = {
                Text(EMAIL)
            },
            placeholder = {
                Text(EMAIL)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = inputPassword,
            onValueChange = {
                inputPassword = it
            },
            label = {
                Text(PASSWORD)
            },
            placeholder = {
                Text(PASSWORD)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            btnClick = true
        }) {
            Text(LOGIN)
        }

        if (btnClick){
            Text("Button Clicked!")
        }
    }
}