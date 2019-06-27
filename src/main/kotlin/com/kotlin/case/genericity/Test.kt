package com.kotlin.case.genericity

import java.util.*
import java.util.Collections.copy
import kotlin.collections.ArrayList

/**
 *@author keith
 *@date 2019-06-26
 *@version 1.0
 */
fun main() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
}

interface Source<out T> {
    //out  只可以被生产，不可以被消费
    fun nextT(): T
}

interface Source2<in T> {
    //in 只可以被消费，不可以被生产
    fun nextT(other: T): Int
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是⼀个 out-参数
}