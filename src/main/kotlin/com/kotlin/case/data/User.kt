package com.kotlin.case.data

/**
 *@author keith
 *@date 2019-06-26
 *@version 1.0
 */
/**
 * 数据类必须有主构造函数并且用var/val修饰
 */
data class User(var name: String) {

    var sex: String = ""
}