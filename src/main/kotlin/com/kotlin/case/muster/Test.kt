package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/2
 *@version 1.0
 */
fun main() {
//    list()
    list2()
//    set()
//    map()

}

fun list() {
    // 只读
    listOf("one", "two", "three", "four").also {
        println("只读：$it")
    }
    //读写
    val s: MutableList<String> = mutableListOf("one", "two", "three", "four")
    s.add("five").also {
        println("读写：$s")
    }
    //基于索引初始化list
    List(3) { it * 2 }.also {
        println("初始化：$it")
    }
    //list集合复制
    val sourceList = mutableListOf(1, 2, 3)
    sourceList.toMutableList().also {
        it.add(4)
        println("Copy size: ${it.size}")
    }
    sourceList.toList().also {
        println("Read-only copy size: ${it.size}")
    }
    sourceList.add(4).also {
        println(sourceList)
    }

}

fun list2() {
    val sourceList = mutableListOf(1, 2, 3)
    val referenceList = sourceList
    referenceList.add(4)
    println("Source size: ${sourceList.size}")

    val sourceList2: MutableList<Int> = mutableListOf(1, 2, 3)//读写
    //指定了返回类型为只读
    val referenceList2: List<Int> = sourceList
    //referenceList2.add(4)            //compilation error
    sourceList2.add(4)
    println(referenceList2)

    //集合转换
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it })
    //索引乘对应的值
    println(numbers.mapIndexed { idx, value -> value * idx })
    //对应值和长度
    val numbers2 = listOf("one", "two", "three", "four")
    println(numbers2.associateWith { it.length })
}

fun set() {
    //只读
    val numbers = setOf(1, 2, 3, 4)
    println(numbers)
    //读写
    val s = mutableSetOf<String>("a", "b", "c")
    s.add("d")
    println(s)
}

fun map() {
    //只读
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    println(numbersMap)
    //读写
    val map = mutableMapOf("one" to 1, "two" to 2)
    // map.put("three", 3)
    map["three"] = 3
    map["one"] = 11
    println(map)
}