package com.kotlin.entity


/**
 *@author keith
 *@date 2019-06-10
 *@version 1.0
 */
class KotlinTest {
    var id: Int = 0
    var name: String? = null
    var sex: String? = null
    var counter = 0 // 注意：这个初始器直接为幕后字段赋值
        set(value) {
            if (value >= 0) field = value
        }

    companion object {
        const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
    }

}

fun main() {
    val l = mutableListOf(1, 2, 3)
    println(l)
    l.swap(0, 2)
    println(l)
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}
