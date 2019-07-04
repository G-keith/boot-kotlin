package com.kotlin.case.sequence

/**
 *@author keith
 *@date 2019/7/3
 *@version 1.0
 */
fun main() {
    demo1()
}

fun demo1() {
    //创建序列
    val numbersSequence = sequenceOf("four", "three", "two", "one")
    //转换成序列
    val numbers = listOf("one", "two", "three", "four")
    val numbersSequence2 = numbers.asSequence()
}