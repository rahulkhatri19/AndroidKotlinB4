package com.example.quicknotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quicknotes.NotesRepo
import com.example.quicknotes.room.NotesDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNotes(navController: NavController){
    var inputTitle by remember { mutableStateOf(TextFieldValue("")) }
    var inputDescription by remember { mutableStateOf(TextFieldValue("")) }
    var isTileValid by remember { mutableStateOf(true) }
    val notesDb = NotesDatabase.getDatabase(LocalContext.current)
    val notesRepo = NotesRepo(notesDb.notesDao())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
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
                },
                actions = {

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                onClick = {
                    notesRepo.saveNote(inputTitle.text, inputDescription.text)
                    navController.popBackStack()
                }

            ) {
                Icon(Icons.Default.Done, "")

            }
        },
    ){ innerPadding ->

        Column {
            Spacer(Modifier.height(72.dp).padding(innerPadding))

            OutlinedTextField(
                value = inputTitle,
                onValueChange = { it ->
                    inputTitle = it
                    isTileValid = inputTitle.text.length > 2
                },
                textStyle = TextStyle.Default.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                placeholder = {
                    Text("Enter Title",
                        fontSize = 24.sp, fontWeight = FontWeight.Bold
                    )
                },
                label = {
                    Text("Enter Title"
                    )
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )

            OutlinedTextField(
                value = inputDescription,
                onValueChange = {
                    inputDescription = it
                },
                placeholder = {
                    Text("Enter Description")
                },
                label = {
                    Text("Enter Description")
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )

//            if (!isTileValid){
//                Text("Invalid Title", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
//            }
        }

    }
}





















