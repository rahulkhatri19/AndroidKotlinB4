package com.example.quicknotes

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicknotes.ui.theme.NoteColoFour
import com.example.quicknotes.ui.theme.NoteColoOne
import com.example.quicknotes.ui.theme.NoteColoThree
import com.example.quicknotes.ui.theme.NoteColoTwo

@Composable
fun QuickNote(modifier: Modifier) {

    val categoryList = arrayListOf("All (20)", "Bookmark", "Important", "Urgent")
    val notesList = arrayListOf<NotesModel>()

    notesList.add(NotesModel(
        "Buy Honey 100% original",
        "Buy new brand honey for family"))

    notesList.add(NotesModel(
        "Plan for today",
        "Good food, Gym, Meeting"))

    notesList.add(NotesModel(
        "Tax payment before the end of march",
        "This is a reminder note, so as not to forgot to pay taxes before the end of march."))

    notesList.add(NotesModel(
        "Password wifi cafe near me",
        "Wifi indoor : to the reset the wifi password on time to dont get confusion after every 4 week."))

    Box(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(Modifier.height(56.dp))
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(32.dp)
                )

                Text(
                    "Hi!, Geek",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }

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

                        })
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

@Preview(showBackground = true)
@Composable
fun demoNotes() {
    QuickNote(Modifier)
}