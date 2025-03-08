package com.example.unituitesting

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun testAdd(){

        val calculator = Calculator()
        val result = calculator.add(4,6)
        assertEquals(10, result)

    }

    @Test
    fun testSubtraction(){
        val calculator = Calculator()
        val result = calculator.subtraction(7,6)
        assertEquals(1, result)
    }
}

class Calculator{
    fun add(a:Int, b:Int):Int {
        return a+b
    }

    fun subtraction(a:Int, b:Int):Int {
        return a-b
    }
}