package com.geeksforgeek.elearningapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun MyCoursesScreen() {

    val context = LocalContext.current
    val courseRepoData = CourseRepo().getJsonData(context)
    val myCourseList = CourseRepo().myCoursesData(courseRepoData)

    Column {
        Spacer(Modifier.height(72.dp))

        LazyColumn {
            items(myCourseList){ list ->
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(Modifier.fillMaxWidth().height(140.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(list.courseImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxWidth(.4f).fillMaxHeight()
                        )

                        Column {

                            Text(
                                text = list.courseTitle,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            )

                            val paidOrFree = if(list.isPaid) "Paid" else "Free"
                            Card(
                                modifier = Modifier.padding(8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Text(
                                    paidOrFree,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
