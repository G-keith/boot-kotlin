package com.kotlin.case.muster

/**
 * 分组
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
fun main(){
    val test3=Test3()
    test3.demo1()
    test3.demo2()
}
class Test3{
    //基本函数groupBy()采用lambda函数并返回一个Map
    fun demo1(){
        val numbers = listOf("one", "two", "three", "four", "five")
        println(numbers.groupBy { it.first().toUpperCase() })
        println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
    }

    fun demo2(){
        val numbers = listOf("one", "two", "three", "four", "five", "six","one")
        println(numbers.groupingBy { it.first() }.eachCount())
    }
}