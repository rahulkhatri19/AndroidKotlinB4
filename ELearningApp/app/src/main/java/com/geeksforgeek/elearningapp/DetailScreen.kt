package com.geeksforgeek.elearningapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun DetailScreen() {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.geeksforgeeks.org/img-practice/prod/courses/504/Mobile/Other/Course_DSA_to_Dev_1720846081.webp")
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(height = 240.dp, width = 500.dp)
        )

        Text(
            text = "DSA to Development a Complete Guide",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.padding(8.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text("4.4")
                    Icon(
                        Icons.Default.Star,
                        "",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 8.dp)
                    )
                }
            }

            Text("446K+ Interested geeks")

        }

        Text(
            text = "This course is designed to take you on a transformative journey from mastering Data Structures and Algorithms (DSA) to becoming a proficient developer. Whether you aspire to become a full-stack developer or specialize in a specific technology stack, this course provides the essential building blocks for your coding journey starting right from basic programming to building applications.",
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Enroll Now", color = Color.Green)
        }
    }
}










