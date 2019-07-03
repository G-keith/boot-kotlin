package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/2
 *@version 1.0
 */
fun main() {
//    demo1()
}

//区间
fun demo1() {
    //输出1到4
    for (i in 1..4) print(i)
    //倒序输出
    println()
    for (i in 4 downTo 1) print(i)
    //指定步长
    println()
    for (i in 1..8 step 2) print(i)
    //1到10，不包括10
    println()
    for (i in 1 until 10) print(i)
    println()
    val list= listOf(1,2,3,4,5,6,7,4)
    for(i in 0 until list.size) print(list[i])
}