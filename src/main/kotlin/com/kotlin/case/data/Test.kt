package com.kotlin.case.data

/**
 *@author keith
 *@date 2019-06-26
 *@version 1.0
 */
fun main() {
    //数据类只会比较主构造函数里面的值，toString等方法也只会使用主构造函数的值
    var u = User("张三")
    u.sex = "男"
    println(u)
    println(u.sex)
    println(u.toString())
}