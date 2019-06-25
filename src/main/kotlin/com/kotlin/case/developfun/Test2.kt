package com.kotlin.case.developfun

/**
 *@author keith
 *@date 2019-06-25
 *@version 1.0
 */
open class D {}

class D1 : D() {}
open class C2 {
    open fun D.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D) {
        d.foo() // 调⽤扩展函数
    }
}

class C1 : C2() {
    override fun D.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}

fun main() {
    C2().caller(D()) // 输出 "D.foo in C"
    C1().caller(D()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
    C2().caller(D1()) // 输出 "D.foo in C" —— 扩展接收者静态解析
}