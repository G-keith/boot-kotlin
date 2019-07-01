package com.kotlin.case.function

import java.util.Arrays.asList

/**
 *@author keith
 *@date 2019/7/1
 *@version 1.0
 */
fun main() {
    var b = B()
//    b.foo().also(::println)
//    b.foo(2).also(::println)
    //有默认值的函数。必须通过命名函数调用
    //b.doo(j=30)
    //当⼀个函数调⽤混⽤位置参数与命名参数时，所有位置参数都要放在第⼀个命名参数之前
    //b.doo(i=30,20)
//    b.doo(30,j=20)
    b.coo()
}

open class A {
    open fun foo(i: Int = 10): Int {
        return i
    }
}

class B : A() {
    //覆盖函数不能有默认值
    override fun foo(i: Int): Int {
        return i
    }

    fun doo(i: Int = 13, j: Int) {
        println("i：" + i + "j：" + j)
    }

    fun coo(){
        val a = arrayOf(1, 2, 3)
        //使用伸展（spread）操作符，在数组前面加*号i，实现数组插入
        val list = asList(-1, 0, *a, 4)
        list.forEach(::println)
    }

    //尾递归
    val eps = 1E-10 // "good enough", could be 10^-15
    tailrec fun findFixPoint(x: Double = 1.0): Double
            = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))
}
/**
 * 中缀：infix
 * 尾递归： tailrec
 */