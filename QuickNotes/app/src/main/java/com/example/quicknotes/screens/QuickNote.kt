package com.example.quicknotes.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quicknotes.NotesRepo
import com.example.quicknotes.model.NotesModel
import com.example.quicknotes.NotesRoute.DETAIL_NOTES
import com.example.quicknotes.NotesRoute.EDIT_NOTES
import com.example.quicknotes.R
import com.example.quicknotes.SharedPreference
import com.example.quicknotes.SharedPreference.PROFILE_NAME
import com.example.quicknotes.Utility.BACKGROUND
import com.example.quicknotes.Utility.DESCRIPTION
import com.example.quicknotes.Utility.NOTES_ID
import com.example.quicknotes.Utility.TITLE
import com.example.quicknotes.room.NotesDatabase
import com.example.quicknotes.ui.theme.NoteColoFour
import com.example.quicknotes.ui.theme.NoteColoOne
import com.example.quicknotes.ui.theme.NoteColoThree
import com.example.quicknotes.ui.theme.NoteColoTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickNote(navController: NavController) {

    val categoryList = arrayListOf("All (20)", "Bookmark", "Important", "Urgent")
   // val notesList = arrayListOf<NotesModel>()
    var showUserDialog by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    val sharedPreferences = SharedPreference.instanceSharedPref(LocalContext.current)
    val notesDb = NotesDatabase.getDatabase(LocalContext.current)
    val notesRepo = NotesRepo(notesDb.notesDao())
    val notesList by notesRepo.notesList.collectAsState()

//    notesList.add(
//        NotesModel(
//        "Buy Honey 100% original",
//        "Buy new brand honey for family")
//    )
//
//    notesList.add(
//        NotesModel(
//        "Plan for today",
//        "Good food, Gym, Meeting")
//    )
//
//    notesList.add(
//        NotesModel(
//        "Tax payment before the end of march",
//        "This is a reminder note, so as not to forgot to pay taxes before the end of march.")
//    )
//
//    notesList.add(
//        NotesModel(
//        "Password wifi cafe near me",
//        "Wifi indoor : to the reset the wifi password on time to dont get confusion after every 4 week.")
//    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "Profile image",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(32.dp)
                        )

                        Text(
                            "Hi!, $userName",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            showUserDialog = true
                        }
                    ) {
                        Icon(Icons.Default.Person, "", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Black)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                onClick = {
                    navController.navigate(EDIT_NOTES)
                }
            ) {
                Icon(Icons.Default.Add, "")
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                LaunchedEffect(Unit) {
                    notesRepo.getAllNotes()
                }
                userName = SharedPreference.getPrefString(PROFILE_NAME, sharedPreferences)
                if (showUserDialog){
                    UserDialog(sharedPreferences, onDismissReq = {
                        showUserDialog = false
                    })
                }
                Spacer(Modifier.height(64.dp).padding(innerPadding))

                Text(
                    "My Notes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(Modifier.height(8.dp))
                LazyRow {
                    items(categoryList) { list ->
                        Card(
                            modifier = Modifier.padding(12.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text(
                                text = list,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                LazyColumn {
                    itemsIndexed(notesList) { index, list ->
                        Card(
                            modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(
                                when(index%4){
                                    0 -> NoteColoOne
                                    1 -> NoteColoTwo
                                    2 -> NoteColoThree
                                    3 -> NoteColoFour
                                    else -> NoteColoOne

                                }),
                            onClick = {
                                navController.navigate("$EDIT_NOTES?$TITLE=${list.notesTitle}&$DESCRIPTION=${list.notesDescription}&$BACKGROUND=$index&$NOTES_ID=${list.id}}")
                              //  navController.navigate(EDIT_NOTES)
                                //  navController.navigate("$DETAIL_NOTES?$TITLE=${list.notesTitle}&$DESCRIPTION=${list.notesDescription}&$BACKGROUND=$index")
                            }
                        ) {
                            Column {
                                Text(
                                    text = list.notesTitle,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Text(
                                    text = list.notesDescription,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                )
                            }

                        }

                    }
                }

            }
        }
    }
}

@Composable
fun UserDialog(sharedPreferences: SharedPreferences, onDismissReq:()->Unit){
    var profileName by remember { mutableStateOf(TextFieldValue("")) }
    AlertDialog(
        title = {
            Text("Profile Title")
        },
        text = {
            OutlinedTextField(
                value = profileName,
                onValueChange = {
                    profileName = it
                },
                label = {
                    Text("Profile Name")
                },
                placeholder = {
                    Text("Profile Name")
                }
            )
        },
        onDismissRequest = {
            onDismissReq()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    SharedPreference.savePrefString(PROFILE_NAME, profileName.text.toString(), sharedPreferences)
                    onDismissReq()
                }
            ) {
                Text("Update")
            }
        },
        dismissButton = {
            TextButton(
                onClick =
                {
                    onDismissReq()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun demoNotes() {
//    QuickNote(Modifier)
}











