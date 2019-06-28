package com.kotlin.case.classes.enumeration

/**
 *@author keith
 *@date 2019/6/28
 *@version 1.0
 */
fun main(){
    Enum.Direction.NORTH.also(::println)
    //枚举类名称和位置（顺序）
    Enum.Direction2.EXIST.name.also(::println)
    Enum.Direction2.NOT_EXIST.ordinal.also(::println)
    Enum.Direction2.EXIST.code.also(::println)
    Enum.Direction2.EXIST.value.also(::println)
    Enum.Direction3.RED.value.also(::println)
}