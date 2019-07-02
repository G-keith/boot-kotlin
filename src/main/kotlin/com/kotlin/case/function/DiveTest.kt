package com.kotlin.case.function

/**
 *@author keith
 *@date 2019/7/1
 *@version 1.0
 */
fun main() {
    val list = ArrayList<String>()
    list.add("wsd")
    list.add("asd")
    list.add("wsdserfd")
    list.add("asdadw")
//    list.filter { it.length > 2 }.forEach {
//        var s = it + "在哪"
//        var d = "$it======"
//        println()
//    }
    list.filter { it.length > 4 }.sortedBy { it }.map { it.toUpperCase() }.also {
        println(it)
        it.forEach(::println)
    }
}