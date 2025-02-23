package com.example.netflixapp

data class MovieModel(
    val title:String,
    val description:String,
    val image:String,
    val general : ArrayList<String>,
    val year:String,
    val rating:String
)
