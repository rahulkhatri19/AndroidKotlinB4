package com.example.netflixapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieRepo(val retrofitInterface: RetrofitInterface) {
    var onlyOnNetflix = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var blockBaster = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var trending = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var watchAgain = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())

    fun getData() {
        GlobalScope.launch(Dispatchers.IO) {
            val movieList = retrofitInterface.getMovieList().body() ?: arrayListOf()
            var only = arrayListOf<MovieModel>()
            var block = arrayListOf<MovieModel>()
            var trend = arrayListOf<MovieModel>()
            var watch = arrayListOf<MovieModel>()

            for (i in 0..10){
                only.add(movieList[i])
            }
            onlyOnNetflix.value = only

            for (i in 11..20){
                block.add(movieList[i])
            }
            blockBaster.value = block

            for (i in 21..30){
                trend.add(movieList[i])
            }
            trending.value = trend

            for (i in 31..40){
                watch.add(movieList[i])
            }
            watchAgain.value = watch

        }
    }
}