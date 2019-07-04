package com.kotlin.case.muster

/**
 * 分组，取值
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
fun main(){
    val test3=Test3()
//    test3.demo1()
//    test3.demo2()
//    test3.demo3()
//    test3.demo4()
//    test3.demo5()
    test3.demo6()
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

    fun demo3(){
        val numbers = listOf("fone","three", "ftwo", "three", "four", "five", "six")
        //返回第一个不匹配元素之前的数据
        println(numbers.takeWhile { it.startsWith('f') })
        //返回从开头到最后一个不匹配之后的元素
        println(numbers.takeLastWhile { it != "three" })
        //返回第一个不匹配之后的数据
        println(numbers.dropWhile { it.length == 3 })
        //返回从开头到最后一个不匹配之前的元素
        println(numbers.dropLastWhile { it.contains('i') })
    }
    fun demo4(){
        //分块
        val numbers = (0..12).toList()
        println(numbers.chunked(3))
        println(numbers.chunked(3) { it.sum() })
        println(numbers.windowed(3))
        //长度，步长，元素不足时是否长度时是否展示
        println(numbers.windowed(3, step = 2, partialWindows = true))
        println(numbers.windowed(3) { it.sum() })
        val numbers2 = listOf("one", "two", "three", "four", "five")
        println(numbers2.zipWithNext())
        println(numbers2.zipWithNext { s1, s2 -> s1.length > s2.length})
    }

    fun demo5(){
        //取集合中的单个元素
        val numbers = linkedSetOf("one", "two", "three", "four", "five")
        //elementAt()对于不提供索引访问的集合或者静态不知道提供索引的集合很有用
        //取集合中的第三个元素
        numbers.elementAt(2).also(::println)
        numbers.first().also(::println)
        numbers.last().also(::println)
        //排序
        val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
        numbersSortedSet.also(::println)
        numbersSortedSet.elementAt(0).also(::println)
        //防止访问不存在位置的元素发生异常时使用
        //不存在时返回null
        println(numbers.elementAtOrNull(5))
        //不存在时返回lambad
        println(numbers.elementAtOrElse(5) { index -> "The value for index $index is undefined"})
        //按条件取
        println(numbers.first { it.length > 3 })
        println(numbers.last { it.startsWith("f") })
        //不存在时
        //firstOrNull=find;lastOrNull=findLast
        println(numbers.firstOrNull{ it.length > 3 })
        println(numbers.lastOrNull{ it.startsWith("f") })
    }

    fun demo6(){
        //检测是否存在
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.contains("four"))
        println("zero" in numbers)
        println(numbers.containsAll(listOf("four", "two")))
        println(numbers.containsAll(listOf("one", "zero")))
        val numbers2 = listOf("one", "two", "three", "four", "five", "six")
        //检查集合是否为空
        println(numbers2.isEmpty())
        println(numbers2.isNotEmpty())
        val empty = emptyList<String>()
        println(empty.isEmpty())
        println(empty.isNotEmpty())
    }
}