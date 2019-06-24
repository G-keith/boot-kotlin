package com.kotlin.case.construction

/**
 *@author keith
 *@date 2019-06-24
 *@version 1.0
 */
class Person constructor(name: String) {

    var nameLength: Int = name.length
    var sex: String = ""
    var age: Int = 0

    constructor(name: String, sex: String) : this(name) {
        this.sex = sex
    }

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }


    init {
        println("初始化代码")
    }

    override fun toString(): String {
        return "Person(nameLength=$nameLength, sex='$sex', age=$age)"
    }

}
