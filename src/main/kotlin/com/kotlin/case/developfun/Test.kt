package com.kotlin.case.developfun

/**
 *@author keith
 *@date 2019-06-25
 *@version 1.0
 */
fun main() {

    val c = C()
    c.foo()
    c.a()
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * 如果⼀个类定义有⼀个成员函数与⼀个扩展函数，⽽这两个函数⼜有相同的接收者类型、相同的名字，
 * 都适⽤给定的参数，这种情况 总是取成员函数
 */
class C {
    fun foo() {
        println("member")
    }
}

fun C.foo() {
    println("extension")
}
fun C.a(){
    println("A")
}