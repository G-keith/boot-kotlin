package com.kotlin.case.muster

/**
 *@author keith
 *@date 2019/7/4
 *@version 1.0
 */
fun main() {
    val test4 = Sort()
    test4.demo1()
}

class Sort {
    fun demo1() {
        val list = listOf("ddd", "aaa", "bb", "c")
        val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
        println(list.sortedWith(lengthComparator))
        println(list.sortedWith(compareBy { it.length }))
        //自然排序
        list.sorted().also(::println)
        list.sortedDescending().also(::println)
        //自定义排序
        list.sortedBy { it.length }.also(::println)
        //取最后一个元素倒叙排序
        list.sortedByDescending { it.last() }.also(::println)
        //翻转
        list.reversed().also(::println)//不受原集合影响
        list.asReversed().also(::println)//受原集合影响
        //随机顺序
        list.shuffled().also(::println)
    }

}