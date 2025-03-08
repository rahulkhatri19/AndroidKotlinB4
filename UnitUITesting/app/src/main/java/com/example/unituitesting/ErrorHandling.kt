package com.example.unituitesting

class ErrorHandling {
}

fun main(){
    println("----  try catch block -----")
    val ab = 10
    try{
        println(ab/0)
    } catch (e:Exception){
        println(ab/1)
      // println(e.printStackTrace())
    }

}