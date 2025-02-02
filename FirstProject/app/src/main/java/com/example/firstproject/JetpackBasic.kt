package com.example.firstproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicLayout() {

    var count by remember { mutableStateOf(10) }
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Spacer(Modifier.height(56.dp))
        Row {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = "",
                modifier = Modifier.size(56.dp)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Geek For Geek",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Jetpack Basic Session, And we are working on Layouts, We saw Row and Column and now we will work on Box Layout",
                    color = Color.Magenta,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        Text(
            text = "This comprehensive Java Programming Course covers everything from Java basics, control structures, functions, classes, objects to advanced concepts in Java including Java Collections, Algorithms, etc. Whether you're a complete Java beginner or looking to enhance your Java programming skills, this complete Java course will guide you through every step of your Java journey. Enroll now for expert-led Java training!",
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = {
                count--
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Enroll Now Only $count Seats Left"
            )
        }

        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(color = Color(0xffFFFF00)),
        ) {

            Text(
                text = "Top Left",
                modifier = Modifier.align(Alignment.TopStart),
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )

            Text(
                text = "Top Right",
                modifier = Modifier.align(Alignment.TopEnd),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Center",
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bottom Left",
                modifier = Modifier.align(Alignment.BottomStart),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bottom Left",
                modifier = Modifier.align(Alignment.BottomEnd),
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun demo() {
    BasicLayout()
}