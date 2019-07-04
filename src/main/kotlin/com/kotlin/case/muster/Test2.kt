package com.kotlin.case.muster

/**
 * 集合过滤
 * filter()返回与其匹配的集合元素。对于这两个List和Set，产生的集合是List，对于Map这是一个Map为好。
 *@author keith
 *@date 2019/7/3
 *@version 1.0
 */
fun main() {
    val test2 = Test2()
//    test2.demo1()
//    println("------------------------------------")
//    test2.demo2()
//    println("------------------------------------")
//    test2.demo3()
//    println("------------------------------------")
//    test2.demo4()
//    println("------------------------------------")
    test2.demo5()
}

class Test2 {
    fun demo1() {
        val numbers = listOf("one", "two", "three", "four")
        numbers.filter { it.length > 3 }.also(::println)
    }

    fun demo2() {
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        numbersMap.filter { it.key.endsWith("1") && it.value > 10 }.also(::println)
        //numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}.also(::println)
    }

    fun demo3() {
        val numbers = listOf("one", "two", "three", "four")
        //如果要在过滤器中使用元素位置，请使用filterIndexed()。它采用带有两个参数的谓词：索引和元素的值。
        numbers.filterIndexed { index, s -> index>1 && s.length>3 }.also(::println)
        //按负面条件过滤集合"filterNot"
        numbers.filterNot { it.length>3 }.also(::println)
    }
    fun demo4(){
        //通过过滤给定类型的元素来缩小元素类型"filterIsInstance"
        val numbers = listOf(null, 1, "two", 3.0, "four")
        numbers.filterIsInstance<String>().also(::println)
        //)返回所有非null元素"filterNotNull"
        numbers.filterNotNull().also(::println)
    }

    fun demo5(){
        //划分，符合和不符合
        val numbers = listOf("one", "two", "three", "four")
        //返回两个集合，第一个时符合的，第二个时符合的
        numbers.partition { it.length > 3 }.also(::println)
        //any,none,all
    }
}
