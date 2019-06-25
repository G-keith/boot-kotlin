package com.kotlin.case.interfaces

/**
 *@author keith
 *@date 2019-06-25
 *@version 1.0
 */
fun main() {
Child()
}

interface MyInterface {
    val prop: Int // 抽象的
    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    //重写，给默认值
    override val prop: Int = 2
}