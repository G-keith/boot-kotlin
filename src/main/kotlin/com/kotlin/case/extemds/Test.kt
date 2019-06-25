package com.kotlin.case.extemds

/**
 *@author keith
 *@date 2019-06-25
 *@version 1.0
 */
fun main() {

//    var c = Children(2)
//    c.a()
//    c.b()
//    c.count.also(::println)
//    c.size.also(::println)
//    Derived("测试执行顺序", "测试代码")
}

open class Bases(val name: String) {
    init {
        println("Initializing Base")
    }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    val lastName: String
) : Bases(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int =
        (super.size + lastName.length).also {
            println("Initializing size in Derived: $it")
        }
}