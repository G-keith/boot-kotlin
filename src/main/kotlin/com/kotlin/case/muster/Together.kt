package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
fun main() {
    val together = Together()
//    together.demo1()
    together.demo2()
}

class Together {
    fun demo1() {
        val numbers = listOf(6, 42, 10, 4)
        //自然函数
        numbers.count().also(::println)
        numbers.max().also(::println)
        numbers.min().also(::println)
        numbers.average().also(::println)
        numbers.sum().also(::println)
        //自定义
        numbers.minBy { it / 3 }.also(::println)
        val strings = listOf("one", "two", "three", "four")
        strings.maxBy { it.length }.also(::println)
        //高级应用
        numbers.sumBy { it * 2 }.also(::println)
        numbers.sumByDouble { it.toDouble() / 2 }.also(::println)
    }

    fun demo2() {
        val numbers = listOf(5, 2, 10, 4)
        //fold()采用初始值并将其用作第一步的累计值，而第一步reduce()使用第一个和第二个元素作为第一步的操作参数。
        val sum = numbers.reduce { s, element -> s + element  }
        println(sum)
        val sumDoubled = numbers.fold(0) { s, element -> s + element * 2 }
        println(sumDoubled)

        //加上索引操作
        val sumEven = numbers.foldIndexed(0) { idx,s , element -> if (idx % 2 == 0) s + element else s }
        println(sumEven)
        val sumEvenRight = numbers.foldRightIndexed(0) { idx, element, s -> if (idx % 2 == 0) s + element else s }
        println(sumEvenRight)
    }
}