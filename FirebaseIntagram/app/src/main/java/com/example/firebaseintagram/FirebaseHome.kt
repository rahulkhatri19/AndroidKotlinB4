package com.example.firebaseintagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseHome(navController: NavController) {

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val database = Firebase.database.reference

    val firebaseRepo = FirebaseRepo()
    val postList by firebaseRepo.postList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(R.drawable.ic_instagram_logo),
                            contentDescription = "",
                            modifier = Modifier
                                .size(120.dp)
                                .align(Alignment.Center)

                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            auth.signOut()
                        }
                    ) {
                        Icon(
                            Icons.Default.ExitToApp, tint = Color.Black, contentDescription = ""
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                Modifier.fillMaxSize()
            ) {
                LaunchedEffect(Unit) {
                    firebaseRepo.getPostData(database)
                }
                Spacer(
                    Modifier
                        .height(72.dp)
                        .padding(innerPadding)
                )

                LazyColumn {
                    items(postList) { list ->

                        println("lazy : $list")
                        InstagramPost(list)
                    }
                }
            }
        }
    )


}

@Composable
fun InstagramPost(list: Post) {
    Column(Modifier.fillMaxWidth()) {
        println("Inside Post")
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(list.userImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .padding(12.dp)
            )
            Text(
                text = list.userName ?: "",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(list.postImage)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(height = 240.dp, width = 420.dp)
                .fillMaxWidth()
                .align(
                    Alignment.CenterHorizontally
                )
        )
    }
    Row(Modifier.padding(top = 12.dp, start = 12.dp)) {
        Icon(
            Icons.Default.FavoriteBorder, ""
        )
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.ic_commet), modifier = Modifier.size(18.dp),
            contentDescription = ""
        )
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.ic_share), "",
            modifier = Modifier.size(18.dp)
        )
    }

    Text(
        "${list.like} likes",
        modifier = Modifier.padding(start = 12.dp),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    Text("View all 20 comments", modifier = Modifier.padding(start = 12.dp), fontSize = 11.sp)
    Text("1 min ago", modifier = Modifier.padding(start = 12.dp), fontSize = 10.sp)
    Divider(thickness = 1.dp, color = Color.Gray)
}

@IgnoreExtraProperties
data class Post(
    val userImage: String? = null,
    val postImage: String? = null,
    val userName: String? = null,
    val like: String? = null
)

@IgnoreExtraProperties
data class Story(val userImage: String? = null, val userName: String? = null)











