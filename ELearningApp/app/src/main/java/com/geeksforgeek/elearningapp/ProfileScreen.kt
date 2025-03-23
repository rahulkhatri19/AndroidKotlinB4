package com.geeksforgeek.elearningapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {

    val context = LocalContext.current
    val courseRepoData = CourseRepo().getJsonData(context)
    val profileData = CourseRepo().profileData(courseRepoData)

    Column {
        Spacer(Modifier.height(72.dp))

        val contentList = arrayListOf<String>()
        val profileFollowerList = arrayListOf<String>()

        profileFollowerList.add("Followers")
        profileFollowerList.add("Following")
        profileFollowerList.add("Articles")

        contentList.add("My Course")
        contentList.add("Download")
        contentList.add("Bookmark")

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(70.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = profileData.userName
                        )
                        Text(
                            text = profileData.userHandle
                        )
                        Text(
                            text = profileData.userCollege
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                    ) {
                        items(profileFollowerList) { list ->
                            Column(
                                modifier = Modifier
                                    .border(BorderStroke(1.dp, Color(0xffa6aaad)))
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = "0",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = list
                                )
                            }
                        }
                    }

                }

            }
        }

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Content",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 28.dp)
            )
            LazyColumn {
                items(contentList) { list ->
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                            .border(BorderStroke(1.dp, Color(0xffa6aaad))),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Icon(Icons.Default.Home, "")
                            Text(
                                text = list,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

        }
    }
}