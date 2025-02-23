package com.example.netflixapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.ImageResult
import coil3.request.crossfade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(modifier: Modifier) {

    var movieList = arrayListOf<MovieModel>()
    val retrofitInstance = RetrofitInterface.getInstance()
    val movieRepo = MovieRepo(retrofitInstance)
    val onlyOnNetflix by movieRepo.onlyOnNetflix.collectAsState()
    val blocBaster by movieRepo.blockBaster.collectAsState()
    val trending by movieRepo.trending.collectAsState()
    val watchAgain by movieRepo.watchAgain.collectAsState()

//    movieList.add(
//        MovieModel(
//            title = "The Shawshank Redemption",
//            description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//            image = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_QL75_UX380_CR0,1,380,562_.jpg",
//            general = arrayListOf("Drama"),
//            year = "1994",
//            rating = "9.4"
//        )
//    )
//    movieList.add(
//        MovieModel(
//            title = "The Godfather",
//            description = "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
//            image = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UY562_CR8,0,380,562_.jpg",
//            general = arrayListOf("Crime", "Drama"),
//            rating = "9.2",
//            year = "1972",
//        )
//    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.netflix_logo),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                },
                actions = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                        )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Black)
            )
        }
    ) { innerPadding ->

        LaunchedEffect(Unit) {
            movieRepo.getData()
            val movieApi = retrofitInstance.getMovieList()
            movieList = retrofitInstance.getMovieList().body() ?: arrayListOf()

            println(
                "movie response : ${movieApi.isSuccessful}, ${movieApi.body()}"
            )
        }
        Box(Modifier.background(Color.Black)){
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),

            ) {
                Spacer(Modifier.height(72.dp).padding(innerPadding))
                Text(
                    "Only On Netflix",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,

                )
                Spacer(Modifier.height(16.dp).padding(innerPadding))
                LazyRow {
                    items(onlyOnNetflix){ list ->
                        Card(
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier.wrapContentSize()
                            ){

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(list.image)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(165.dp, 210.dp)
                                )
                                Image(
                                    painter = painterResource(R.drawable.netflix_logo),
                                    contentDescription = "",
                                    modifier = Modifier.size(28.dp))

                            }
                        }
                    }
                }

                NetflixLayout("Blockbuster Action", blocBaster)
                NetflixLayout("Trending Now", trending)
                NetflixLayout("Watch it Again", watchAgain)
            }
        }


    }
}

@Composable
fun NetflixLayout(title:String , movieList:ArrayList<MovieModel>){

    Spacer(Modifier.height(16.dp))

    Text(
        title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.White,

        )
    Spacer(Modifier.height(16.dp))
    LazyRow {
        items(movieList){ list ->
            Card(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Box(
                    modifier = Modifier.wrapContentSize()
                ){

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(list.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp, 180.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.netflix_logo),
                        contentDescription = "",
                        modifier = Modifier.size(28.dp))

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun demo(){
    MovieScreen(Modifier)
}


















