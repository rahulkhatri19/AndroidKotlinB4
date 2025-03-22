package com.geeksforgeek.elearningapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val courseRepoData = CourseRepo().getJsonData(context)
    val courseList = CourseRepo().getHomeCourseData(courseRepoData)

    Column {
        Spacer(Modifier.height(72.dp))
        LazyColumn {
            items(courseList){ list ->
                Card(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    onClick = {
                     //   navController.navigate(DETAIL_SCREEN)
                    }
                ) {
                    Column {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(list.courseImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(height = 180.dp, width = 500.dp)
                        )

                        Text(
                            text = list.courseTitle,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        )

                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Card(
                                modifier = Modifier.padding(8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Row(modifier = Modifier.padding(8.dp)) {
                                    Text(list.rating)
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(20.dp)
                                            .padding(start = 8.dp)
                                    )
                                }
                            }

                            Text(
                                "${list.numberOfGeeks} Interested Geeks",
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }


                        Button(
                            onClick = {
                               // paymentIntent(context, "")
                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,

                                ),
                            shape = RoundedCornerShape(4.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                        ) {
                            Text("Enroll Now", color = Color.Green)
                        }


                    }
                }
            }
        }
    }
}

