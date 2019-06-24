package com.kotlin.case.construction

/**
 *@author keith
 *@date 2019-06-24
 *@version 1.0
 */
fun main() {

    Person("测试构造函数").also(::println)
    Person("次级构造函数", "男").also(::println)
    Person("次级构造函数", 23).also(::println)
    InitOrderDemo("代码执行顺序")
}
