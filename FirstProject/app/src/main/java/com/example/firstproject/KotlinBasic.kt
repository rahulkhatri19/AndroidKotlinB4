package com.example.firstproject

//fun main() {
//    println("Hello kotlin")
//
//   // dataType()
////    operators()
////    controlFlow()
//    checkAnB(6,8)
//
//    val greaterAnB : (Int, Int ) -> String = { a,b ->
//        if (a>b) "a is greater than b" else "b is greater than a"
//    }
//    val addAnB : (Int, Int, Int ) -> String = { a,b,c ->
//        ""
//    }
//
//    println( greaterAnB(9,8) )
//    println( addAnB(9,8,10) )
//
//}

fun checkAnB(a:Int, b: Int):String{
return ""
}

fun controlFlow() {
    var a = 3
    val b = 8

    if(a>b){
        println(" a is greater than b")
    } else if (a==b){
        println("a is equal to b")
    }
    else {
        println(" a is not greater than b")
    }

    when(a){
        is Int -> println("is int")
    }

    when {
       a>b -> println("a is greater than b")
       a==b -> println("a is equal to b")
       else -> println(" a is not greater than b")
    }

    val list = listOf(1, 2, 3, 4, 66, 88, 4, 7)
    for(i in list.indices){
        println("i=$i")
        if(i==5){
            break
        }
    }

    for (i in 10 downTo 4){
        println("i is = $i")
    }

    while(a<b) {
        println("print a=$a")
        a++
    }

}

fun operators() {
    // + - * / %
    // arthimatic

    var a = 15.0
    val b = 4.0
    val isStudent = false

    val add = a+b
    val sub = a-b
    val mul = a*b
    val divide = a/b
    val modulus = a%b
    println("add=$add, sub=$sub, mul=$mul, div=$divide, mod=$modulus")

    // comparison  == != < > <= >=
    // a==b is a equal to b
    // a != b is a not equal to b
    // a < b is a less than b

//    if (a==b){
//        println("a and b are equal")
//    }
//    if (a!=b){
//        println("is a not equal to b")
//    }
//    if (a< b){
//        println("a < b is a less than b")
//    }
    if (a > b){
        println("a < b is a greater than b")
    }
    if (a >= b){
        println("a <= b is a greater than or equal")
    }

    // assignment operator = +=-=

    println("a = $a, b=$b")
    a=b
    println("a = $a, b=$b")
//    a = a+2
//    a+=2
//    a= a-3
//    a-=3

    // to increment by one a++
    println("$a")

    // logical operator || && !
    println("isStudent = $isStudent")
    println("isStudent = ${!isStudent}")
    val c = 5
    val d = 4

    if (c>d || c==5){
        println("a>b or a is 5")
    }

    if (c>d && c==5){
        println("a>b and a is 5")
    }




}

fun dataType() {
    val one = 1f
    var name = "Rahul"
    val isStudent = false

    println("$one, $name, $isStudent")

    name = "Geeksforgeek"

    println("$one, $name")
}


// classes

class KotlinBasic {

    val person = "Geek for Geek"
    val listStudent:ArrayList<Student> = arrayListOf<Student>()
    companion object {
        const val PASSING_MARKS = 60
    }
}




data class Student(
    val name :String,
    val id:Int,
    val tenthPercentage:Double,
    val gardenName:String
)

enum class GeeK(level:Int) {
    BIGGNER(4), EXPERT(9), PROFESHIONAL(10)
}

class GeekForGeek {

}

//fun main() {
//    val student = ""
//    when(student){
//        GeeK.EXPERT -> ""
//        GeeK.BIGGNER.name -> ""
//        GeeK.PROFESHIONAL.name -> ""
//
//    }
//}


open class Animal {
    open fun numberOfLeg(){
        println("2")
    }
    open fun makeSound(){
        println("Meow")
    }
}

class Dod : Animal() {
    override fun makeSound() {
        println("Bhov")
    }
}

class Cat : Animal(), Shapes{
    override fun numberOfLeg() {
        println("4")
    }

    override fun radius(): Double {
        return 6.0
    }

    override fun area(a: Int, b: Int): Double {
        return (a-b).toDouble()
    }

}

fun main() {
    val dog = Dod()
    val cat = Cat()

    cat.makeSound()
    cat.numberOfLeg()

    dog.numberOfLeg()
    dog.makeSound()
}

interface Shapes {
    fun radius(): Double
    fun area(a:Int, b:Int):Double {
        return (a*b).toDouble()
    }
}

