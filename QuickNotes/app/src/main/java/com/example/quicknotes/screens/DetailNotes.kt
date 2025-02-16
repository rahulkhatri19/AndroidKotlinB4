package com.example.quicknotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quicknotes.R
import com.example.quicknotes.ui.theme.NoteColoFour
import com.example.quicknotes.ui.theme.NoteColoOne
import com.example.quicknotes.ui.theme.NoteColoThree
import com.example.quicknotes.ui.theme.NoteColoTwo

@Composable
fun DetailNotes(navController: NavController, title: String, description: String, background:Int) {
    println("Detail Note: $background")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when(background%4){
                    0 -> NoteColoOne
                    1 -> NoteColoTwo
                    2 -> NoteColoThree
                    3 -> NoteColoFour
                    else -> NoteColoOne
                }
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    "",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
//stringResource(R.string.notes_title)
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun demoNotesDetail() {
//    DetailNotes(Modifier)
}