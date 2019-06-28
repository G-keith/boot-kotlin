package com.kotlin.case.classes.nest

/**
 *@author keith
 *@date 2019/6/28
 *@version 1.0
 */
fun main(){

    Outer.Nested().foo().also(::println)
    Outer2().Inner().foo().also(::println)
}

//嵌套类
class Outer {
    private val bar: Int = 1
    class Nested {
        //不可以访问外部你类成员
        fun foo() = 2
    }
}

//内部类
class Outer2 {
    private val bar: Int = 1
    inner class Inner {
        //可以访问外部类成员
        fun foo() = bar
    }
}