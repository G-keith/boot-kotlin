package com.kotlin.case.extemds

/**
 *@author keith
 *@date 2019-06-24
 *@version 1.0
 */
class Children(override var count: Int) : Base() {

    val size: Int = (count + 1).also { println("$it") }
    //禁止下级覆盖，可以加final关键字
    override fun a() {
        println("子级A")
    }
    //父类没加open的函数，子类不能定义相同的函数并且不能重写
}

//class Children2 : Base {
////    constructor(c: Int) : super(c)
////
////}