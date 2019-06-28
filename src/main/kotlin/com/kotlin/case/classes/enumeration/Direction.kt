package com.kotlin.case.classes.enumeration

/**
 *@author keith
 *@date 2019/6/28
 *@version 1.0
 */
class Enum {
    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class Direction2 constructor(val code: Int = 0, val value: String = "") {
        EXIST(1, "存在"),
        NOT_EXIST(2, "不存在");
    }

    enum class Direction3 constructor(val value: Int=0){
        ONE(1),
        TWO(2),
        RED(0xFF0000)
    }
}




